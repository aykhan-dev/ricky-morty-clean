package reddit.task.data.mapper

import reddit.task.data.remote.pojo.CharacterPOJO
import reddit.task.data.room.entity.CharacterEntity

fun CharacterPOJO.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        location = location.name,
        origin = origin.name,
        imageUrl = image,
        status = status,
        species = species,
    )
}