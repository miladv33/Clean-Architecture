package com.example.quotes.remote.service

import com.example.quotes.data.dto.RandomQuoteDTO
import retrofit2.http.GET

interface RandomQuoteApi {

    @GET("random")
    suspend fun getRandomQuote(): RandomQuoteDTO
}

