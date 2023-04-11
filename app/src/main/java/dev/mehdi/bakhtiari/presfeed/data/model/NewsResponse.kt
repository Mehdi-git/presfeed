package dev.mehdi.bakhtiari.presfeed.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "status") val status: String?,
    @Json(name = "totalResults") val totalResults: Int?,
    @Json(name = "articles") val articles: List<Article>?
)
