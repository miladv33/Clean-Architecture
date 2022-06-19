package com.example.takehomeassesmenttestnumber1.data.mapper

import java.lang.Exception

interface Mapper<I, O> {
    fun map(input: I): O
    fun mapFailure(exception: Exception): O
}