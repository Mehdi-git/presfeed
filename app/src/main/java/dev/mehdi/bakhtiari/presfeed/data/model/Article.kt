package dev.mehdi.bakhtiari.presfeed.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "author") val author: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "urlToImage") val urlToImage: String?,
    @Json(name = "publishedAt") val publishedAt: String?,
    @Json(name = "content") val content: String?,
    @Json(name = "source") val source: Source?,
)

fun Article.toArticleUiModel(): ArticleUiModel {
    return ArticleUiModel(
        imageUrl = urlToImage,
        title = title,
        description = description,
        content = content,
    )
}