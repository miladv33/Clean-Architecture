package com.example.quotes.data.mapper.base


/**
 *
 * @param T
 */
interface BaseMapper<T> {
    fun mapFailure(exception: Exception): Result<T> = Result.failure(exception)
}