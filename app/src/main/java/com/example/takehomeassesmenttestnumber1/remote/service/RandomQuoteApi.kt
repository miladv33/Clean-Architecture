package com.example.takehomeassesmenttestnumber1.remote.service

import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import retrofit2.http.GET

interface RandomQuoteApi {

    @GET("random")
    suspend fun getRandomQuote(): Result<RandomQuote>
}

