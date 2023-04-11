package dev.mehdi.bakhtiari.presfeed.data.repository


import dev.mehdi.bakhtiari.presfeed.data.model.NewsResponse
import dev.mehdi.bakhtiari.presfeed.data.remote.NewsService
import javax.inject.Inject
import dev.mehdi.bakhtiari.presfeed.data.remote.ApiResult

class RepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : Repository {

    override suspend fun getTopHeadline(source: String): ApiResult<NewsResponse> {
         return try {
            val response = newsService.getTopHeadline(source)
            if (response.isSuccessful) {
                val responseBody = response.body()

                if (responseBody != null) {
                     ApiResult.Success(responseBody)
                } else {
                     ApiResult.Error("data is null")
                }

            } else {
                 ApiResult.Error(response.message())
            }

        } catch (e: Exception) {
              ApiResult.Error(e.message ?: "Unknown Error!" )
        }
    }
}