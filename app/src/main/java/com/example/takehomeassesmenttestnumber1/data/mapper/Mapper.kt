package com.example.takehomeassesmenttestnumber1.data.mapper

import java.lang.Exception

interface Mapper<DTO, Model> {
    fun map(input: DTO): Result<Model>
    fun mapFailure(exception: Exception): Result<Model>
    fun convertModelToDTO(model: Model): DTO
}