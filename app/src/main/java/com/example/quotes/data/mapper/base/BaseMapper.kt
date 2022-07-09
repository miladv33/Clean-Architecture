package com.example.quotes.data.mapper.base


interface BaseMapper<T> {
    fun mapFailure(exception: Exception): Result<T> = Result.failure(exception)
}