package com.example.quotes.di

import com.example.quotes.data.mapper.RandomQuoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Mapper {

    @Provides
    fun provideRandomQuoteMapper(): RandomQuoteMapper {
        return RandomQuoteMapper()
    }
}