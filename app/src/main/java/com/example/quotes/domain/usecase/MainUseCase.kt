package com.example.quotes.domain.usecase

import com.example.quotes.data.model.RandomQuote
import com.example.quotes.data.repository.RandomQuoteRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class MainUseCase @Inject constructor(
    private var randomQuoteRepository: RandomQuoteRepository
) {


    fun executeQuotesFlow() = flow {
        emit(randomQuoteRepository.getOffline())
        emit(randomQuoteRepository.getFromServer())
    }

    fun executeRandomQuoteInsert(testDataRandomQuote: RandomQuote) = runBlocking{
        randomQuoteRepository.insertToDatabase(testDataRandomQuote)
    }
}
