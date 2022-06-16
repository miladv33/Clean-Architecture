package com.example.takehomeassesmenttestnumber1.di

import com.example.takehomeassesmenttestnumber1.data.mapper.RandomQuoteMapper
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