package reddit.task.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import reddit.task.data.room.dao.CharactersDao
import reddit.task.data.room.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val charactersDao: CharactersDao
}