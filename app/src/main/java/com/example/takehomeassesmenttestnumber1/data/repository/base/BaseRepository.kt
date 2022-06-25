package com.example.takehomeassesmenttestnumber1.data.repository.base

import com.example.takehomeassesmenttestnumber1.data.mapper.Mapper
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote

abstract class BaseRepository<T>(val mapper: Mapper<*, T>) {
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