package dev.mehdi.bakhtiari.presfeed.data.repository

import dev.mehdi.bakhtiari.presfeed.data.model.NewsResponse
import dev.mehdi.bakhtiari.presfeed.data.remote.ApiResult

interface Repository {

   suspend fun getTopHeadline(source: String) : ApiResult<NewsResponse>

}