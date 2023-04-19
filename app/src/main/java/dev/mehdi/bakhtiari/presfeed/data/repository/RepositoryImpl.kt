package dev.mehdi.bakhtiari.presfeed.data.repository


import dev.mehdi.bakhtiari.presfeed.data.model.NewsResponse
import dev.mehdi.bakhtiari.presfeed.data.remote.NewsService
import javax.inject.Inject
import dev.mehdi.bakhtiari.presfeed.data.remote.ApiResult
import dev.mehdi.bakhtiari.presfeed.utils.NetworkConnectivityChecker

class RepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val connectivityChecker: NetworkConnectivityChecker
) : Repository {

    override suspend fun getTopHeadline(source: String): ApiResult<NewsResponse> {
        if (connectivityChecker.isNetworkAvailable().not()) {
            return ApiResult.Error("No connectivity to the network")
        } else {
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
                ApiResult.Error(e.message ?: "Unknown Error!")
            }
        }
    }
}