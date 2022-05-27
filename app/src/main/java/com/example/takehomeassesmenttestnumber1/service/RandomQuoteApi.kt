package com.example.takehomeassesmenttestnumber1.service

import com.example.takehomeassesmenttestnumber1.model.RandomQuote
import retrofit2.http.GET

interface RandomQuoteApi {

    @GET("random")
    suspend fun getRandomQuote(): Result<RandomQuote>
}

