package reddit.task.domain.repository

import kotlinx.coroutines.flow.Flow
import reddit.task.domain.model.Character

interface CharactersRepository {

    fun getAll(): Flow<List<Character>>

    suspend fun fetch(page: String?): String?

}