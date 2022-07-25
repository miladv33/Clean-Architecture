package com.example.quotes.data.repository.base

import com.example.quotes.data.mapper.base.Mapper
import com.example.quotes.data.model.Model

/**
 *
 * @param T : Model
 * @property mapper Mapper<*, T>
 * @constructor
 */
abstract class BaseRepository<T : Model>(val mapper: Mapper<*, T>) {
    /**
     * to handle any exception
     * @param call SuspendFunction0<Result<T>>
     * @return Result<T>
     */
    suspend fun safeCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            call.invoke()
        } catch (e: Exception) {
            mapper.mapFailure(e)
        }
    }

    /**
     * call data from server
     * @return Result<T>
     */
    abstract suspend fun getFromServer(): Result<T>

    /**
     * call data from local storage
     * @return Result<T>
     */
    abstract suspend fun getOffline(): Result<T>

    /**
     * insert data to data base
     * @param testDataRandomQuote T
     */
    abstract suspend fun insertToDatabase(testDataRandomQuote: T)

}