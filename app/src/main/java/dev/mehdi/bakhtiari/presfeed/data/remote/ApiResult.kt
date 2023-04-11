package dev.mehdi.bakhtiari.presfeed.data.remote

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T): ApiResult<T>()
    data class Error(val message: String?): ApiResult<Nothing>()
}
