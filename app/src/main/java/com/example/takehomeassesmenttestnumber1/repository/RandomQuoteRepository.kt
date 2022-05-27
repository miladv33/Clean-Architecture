package com.example.takehomeassesmenttestnumber1.repository

import com.example.takehomeassesmenttestnumber1.model.RandomQuote
import com.example.takehomeassesmenttestnumber1.service.RandomQuoteApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

// TODO: this class must implement from an Interface
@Module
@InstallIn(ViewModelComponent::class)
class RandomQuoteRepository @Inject constructor(
    private var randomQuoteApi: RandomQuoteApi
) {
    suspend fun getRandomFlow(): Result<RandomQuote> {
        return (randomQuoteApi.getRandomQuote())
    }
}
