package com.example.quotes.data.mapper.base

import com.example.quotes.data.enum.Error
import com.example.quotes.data.model.*
import java.lang.Exception

interface Mapper<DTO, T : Model> : BaseMapper<T> {

    fun map(input: DTO?): Result<T> {
        return if (input != null) {
            Result.success(checkNullable(input))
        } else {
            mapFailure(CustomException(Error.NullObject))
        }
    }

    private fun checkNullable(input: DTO?): T {
        return input?.let {
            return createModelFromDTO(it)
        } ?: kotlin.run {
            Model() as T
        }
    }

    fun createModelFromDTO(input: DTO): T

    fun convertModelToDTO(model: T): DTO? = null
}