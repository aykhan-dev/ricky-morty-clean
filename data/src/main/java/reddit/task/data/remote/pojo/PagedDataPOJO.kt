package reddit.task.data.remote.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagedDataPOJO<T>(
    @SerialName("info") val info: Info,
    @SerialName("results") val results: T
)

@Serializable
data class Info(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("pages") val pages: Int,
    @SerialName("prev") val prev: String?
)