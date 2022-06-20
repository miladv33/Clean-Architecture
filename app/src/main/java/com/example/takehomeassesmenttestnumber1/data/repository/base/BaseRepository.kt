package com.example.takehomeassesmenttestnumber1.data.repository.base

import com.example.takehomeassesmenttestnumber1.data.mapper.Mapper
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote

open class BaseRepository<T>(val mapper: Mapper<*, Result<T>>) {
    suspend fun safeCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            call.invoke()
        } catch (e: Exception) {
            mapper.mapFailure(e)
        }
    }
}