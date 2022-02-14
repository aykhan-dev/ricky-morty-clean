package reddit.task.data.repository

import reddit.task.data.local.characters.CharactersLocalDataSource
import reddit.task.data.mapper.toEntity
import reddit.task.data.remote.CharactersRemoteDataSource
import reddit.task.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import reddit.task.domain.model.Character

class CharactersRepositoryImpl(
    private val localDataSource: CharactersLocalDataSource,
    private val remoteDataSource: CharactersRemoteDataSource,
) : CharactersRepository {

    override fun getAll(): Flow<List<Character>> {
        return localDataSource.getAll()
    }

    /*
         fetch() function is used in both initial and infinite scrolling states;
         if the next page url is empty it means that it is initial state;
         after fetching the initial page, each time the url of next page is known for next loads;

         There is a null state when there is no next pages, but this case is handled by view model side;
     */
    override suspend fun fetch(page: String?): String? {
        val remoteData = if(page == null) remoteDataSource.fetchInitial() else remoteDataSource.fetchPage(page)
        val characters = remoteData.results
        val entities = characters.map { it.toEntity() }

        localDataSource.addAll(entities)
        return remoteData.info.next
    }

}