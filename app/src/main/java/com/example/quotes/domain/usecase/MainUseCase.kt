package com.example.quotes.domain.usecase

import com.example.quotes.data.model.RandomQuote
import com.example.quotes.data.repository.RandomQuoteRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Main use case
 *
 * @property randomQuoteRepository
 * @constructor Create empty Main use case
 */
@Module
@InstallIn(ViewModelComponent::class)
class MainUseCase @Inject constructor(
    private var randomQuoteRepository: RandomQuoteRepository
) {


    /**
     * Execute quotes flow
     *
     */
    fun executeQuotesFlow() = flow {
        emit(randomQuoteRepository.getOffline())
        emit(randomQuoteRepository.getFromServer())
    }

    /**
     * Execute random quote insert
     *
     * @param testDataRandomQuote
     */
    fun executeRandomQuoteInsert(testDataRandomQuote: RandomQuote) = runBlocking{
        randomQuoteRepository.insertToDatabase(testDataRandomQuote)
    }
}
