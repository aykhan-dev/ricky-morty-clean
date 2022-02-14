package reddit.task.data.mapper

import reddit.task.data.room.entity.CharacterEntity
import reddit.task.domain.model.Character

fun CharacterEntity.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = "$status - $species",
        location = location,
        firstSight = origin,
        imageUrl = imageUrl,
    )
}