package dev.mehdi.bakhtiari.presfeed.data.model

import com.squareup.moshi.Json

data class Source(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
)
