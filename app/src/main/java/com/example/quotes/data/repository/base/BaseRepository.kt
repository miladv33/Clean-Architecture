package com.example.quotes.data.repository.base

import com.example.quotes.data.mapper.base.Mapper
import com.example.quotes.data.model.Model


abstract class BaseRepository<T : Model>(val mapper: Mapper<*, T>) {
    suspend fun safeCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            call.invoke()
        } catch (e: Exception) {
            mapper.mapFailure(e)
        }
    }

    abstract suspend fun getFromServer(): Result<T>
    abstract suspend fun getOffline(): Result<T>
    abstract suspend fun insertToDatabase(testDataRandomQuote: T)

}