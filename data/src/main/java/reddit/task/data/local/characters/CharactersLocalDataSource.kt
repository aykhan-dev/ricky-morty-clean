package reddit.task.data.local.characters

import reddit.task.data.room.entity.CharacterEntity
import reddit.task.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {

    fun getAll(): Flow<List<Character>>

    suspend fun addAll(characters: List<CharacterEntity>)

}