package com.test.network.model.mapper

import com.test.network.model.NetworkResponse

sealed class NetworkResultResult<out T : Any> {
    /**
     * Success
     */
    data class Success<T : Any>(val result: T?) : NetworkResultResult<T>()

    /**
     * Failure
     */
    data class Fail<U : Any>(val errorResult: U) : NetworkResultResult<Nothing>()

    /**
     * Network error
     */
    data class Exception(val error: Throwable?) : NetworkResultResult<Nothing>()
}

fun <T : Any> NetworkResponse<T, Any>.toRepositoryResult(): NetworkResultResult<T> {
    return when (this) {
        is NetworkResponse.Success -> NetworkResultResult.Success(this.body)
        is NetworkResponse.ApiError -> NetworkResultResult.Fail(this)
        is NetworkResponse.NetworkError -> NetworkResultResult.Exception(this.error)
        is NetworkResponse.UnknownError -> NetworkResultResult.Exception(this.error)
    }
}

fun <T : Any, U : Any> NetworkResponse<T, Any>.toRepositoryResult(mapper: (from: T) -> U): NetworkResultResult<U> {
    return when (this) {
        is NetworkResponse.Success -> NetworkResultResult.Success(
            if (this.body != null)
                mapper(this.body)
            else null
        )
        is NetworkResponse.ApiError -> NetworkResultResult.Fail(this)
        is NetworkResponse.NetworkError -> NetworkResultResult.Exception(this.error)
        is NetworkResponse.UnknownError -> NetworkResultResult.Exception(this.error)
    }
}
