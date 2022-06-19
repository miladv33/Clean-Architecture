package com.example.takehomeassesmenttestnumber1.di

import com.example.takehomeassesmenttestnumber1.data.dao.RandomQuoteDao
import com.example.takehomeassesmenttestnumber1.data.mapper.RandomQuoteMapper
import com.example.takehomeassesmenttestnumber1.di.annotation.RetrofitQuotableServer
import com.example.takehomeassesmenttestnumber1.data.repository.RandomQuoteRepository
import com.example.takehomeassesmenttestnumber1.remote.service.RandomQuoteApi
import com.example.takehomeassesmenttestnumber1.remote.adapter.ResultCallAdapterFactory
import com.example.takehomeassesmenttestnumber1.domain.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @RetrofitQuotableServer
    @Provides
    @Singleton
    fun provideQuotableServerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    /**
     *
     * @param retrofit must ber quotable server Retrofit
     * @return RandomQuoteApi
     */
    @Provides
    @Singleton
    fun provideRandomQuoteApi(@RetrofitQuotableServer retrofit: Retrofit): RandomQuoteApi {
        return retrofit.create(RandomQuoteApi::class.java)
    }

    /**
     *
     * @param randomQuoteApi RandomQuoteApi
     * @return RandomQuoteRepository
     */
    @Provides
    @Singleton
    fun provideRandomQuoteRepository(randomQuoteApi: RandomQuoteApi,
                                     randomQuoteDao: RandomQuoteDao,
                                     randomQuoteMapper: RandomQuoteMapper): RandomQuoteRepository {
        return RandomQuoteRepository(randomQuoteApi, randomQuoteDao,randomQuoteMapper)
    }

    /**
     *
     * @param randomQuoteRepository RandomQuoteRepository
     * @return MainShowCase
     */
    @Provides
    @Singleton
    fun provideMainShowCase(randomQuoteRepository: RandomQuoteRepository): MainUseCase {
        return MainUseCase(randomQuoteRepository)
    }

}