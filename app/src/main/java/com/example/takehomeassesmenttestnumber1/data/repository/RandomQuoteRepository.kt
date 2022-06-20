package com.example.takehomeassesmenttestnumber1.data.repository

import com.example.takehomeassesmenttestnumber1.data.dao.RandomQuoteDao
import com.example.takehomeassesmenttestnumber1.data.mapper.RandomQuoteMapper
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import com.example.takehomeassesmenttestnumber1.data.repository.base.BaseRepository
import com.example.takehomeassesmenttestnumber1.remote.service.RandomQuoteApi
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
    suspend fun getRandomFlow() = safeCall {
        randomQuoteMapper.map(randomQuoteApi.getRandomQuote())
    }

    suspend fun getOfflineRandomFlow() = safeCall {
        randomQuoteMapper.map(randomQuoteDao.getRandomQuotes())
    }
}
