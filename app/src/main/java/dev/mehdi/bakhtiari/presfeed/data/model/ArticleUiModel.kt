package dev.mehdi.bakhtiari.presfeed.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleUiModel (
    val imageUrl: String?,
    val title: String?,
    val description: String?,
    val content: String?,
) : Parcelable
