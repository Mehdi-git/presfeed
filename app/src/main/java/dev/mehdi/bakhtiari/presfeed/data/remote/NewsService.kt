package dev.mehdi.bakhtiari.presfeed.data.remote

import dev.mehdi.bakhtiari.presfeed.BuildConfig
import dev.mehdi.bakhtiari.presfeed.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("sources") sources: String,
        @Query("apikey") apikey: String = BuildConfig.API_KEY,
    ) : Response<NewsResponse>
}