package reddit.task.domain.model

data class Character(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val status: String,
    val location: String,
    val firstSight: String,
)