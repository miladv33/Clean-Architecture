package com.example.quotes.data.mapper.base

import com.example.quotes.data.enum.Error
import com.example.quotes.data.model.*
import java.lang.Exception

/**
 *
 * @param DTO
 * @param T : Model
 */
interface Mapper<DTO, T : Model> : BaseMapper<T> {
    /**
     * for null check and map the result
     * @param input DTO?
     * @return Result<T>
     */
    fun map(input: DTO?): Result<T> {
        return if (input != null) {
            Result.success(checkNullable(input))
        } else {
            mapFailure(CustomException(Error.NullObject))
        }
    }

    /**
     * check input is null or not
     * @param input DTO?
     * @return T
     */
    private fun checkNullable(input: DTO?): T {
        return input?.let {
            return createModelFromDTO(it)
        } ?: kotlin.run {
            Model() as T
        }
    }

    /**
     * change DTO to model
     * @param input DTO
     * @return T
     */
    fun createModelFromDTO(input: DTO): T

    /**
     * change model to dto
     * @param model T
     * @return DTO?
     */
    fun convertModelToDTO(model: T): DTO? = null
}