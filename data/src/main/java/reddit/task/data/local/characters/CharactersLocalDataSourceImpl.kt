package reddit.task.data.local.characters

import reddit.task.data.room.AppDatabase
import reddit.task.data.room.entity.CharacterEntity
import reddit.task.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import reddit.task.data.mapper.toDomain

class CharactersLocalDataSourceImpl(database: AppDatabase): CharactersLocalDataSource {

    private val charactersDao = database.charactersDao

    override fun getAll(): Flow<List<Character>> {
        return charactersDao.getAllCharacters()
            .map { it.map { characterEntity ->  characterEntity.toDomain() } }
    }

    override suspend fun addAll(characters: List<CharacterEntity>) {
        charactersDao.addCharacters(*characters.toTypedArray())
    }

}