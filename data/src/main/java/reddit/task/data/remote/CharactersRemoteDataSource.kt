package reddit.task.data.remote

import reddit.task.data.remote.pojo.CharacterPOJO
import reddit.task.data.remote.pojo.PagedDataPOJO
import retrofit2.http.GET
import retrofit2.http.Url

interface CharactersRemoteDataSource {

    @GET("character")
    suspend fun fetchInitial(): PagedDataPOJO<List<CharacterPOJO>>

    @GET
    suspend fun fetchPage(@Url url: String): PagedDataPOJO<List<CharacterPOJO>>

}