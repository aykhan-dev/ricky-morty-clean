package reddit.task.data.remote.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterPOJO(
    @SerialName("created") val created: String,
    @SerialName("gender") val gender: String,
    @SerialName("id") val id: Int,
    @SerialName("image") val image: String,
    @SerialName("location") val location: Location,
    @SerialName("name") val name: String,
    @SerialName("origin") val origin: Origin,
    @SerialName("species") val species: String,
    @SerialName("status") val status: String,
    @SerialName("type") val type: String
)

@Serializable
data class Location(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Origin(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)