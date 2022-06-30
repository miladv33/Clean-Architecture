package com.example.quotes.data.repository

import com.example.quotes.data.dao.RandomQuoteDao
import com.example.quotes.data.mapper.RandomQuoteMapper
import com.example.quotes.data.model.RandomQuote
import com.example.quotes.data.repository.base.BaseRepository
import com.example.quotes.remote.service.RandomQuoteApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

// TODO: this class must implement from an Interface
@Module
@InstallIn(ViewModelComponent::class)
class RandomQuoteRepository @Inject constructor(
    private var randomQuoteApi: RandomQuoteApi,
    private var randomQuoteDao: RandomQuoteDao,
    private var randomQuoteMapper: RandomQuoteMapper
) : BaseRepository<RandomQuote>(randomQuoteMapper) {

    override suspend fun getFromServer() = safeCall {
        val randomQuote = randomQuoteApi.getRandomQuote()
        randomQuoteDao.insert(randomQuote)
        randomQuoteMapper.map(randomQuote)
    }

    override suspend fun getOffline() = safeCall {
        randomQuoteMapper.map(randomQuoteDao.getRandomQuotes())
    }

    override suspend fun insertToDatabase(testDataRandomQuote: RandomQuote) {
        randomQuoteMapper.convertModelToDTO(testDataRandomQuote)?.let { randomQuoteDao.insert(it) }
    }
}
