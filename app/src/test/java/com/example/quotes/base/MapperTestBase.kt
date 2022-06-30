package com.example.quotes.base

import android.content.Context
import com.example.quotes.data.dao.RandomQuoteDao
import com.example.quotes.data.dto.RandomQuoteDTO
import com.example.quotes.data.mapper.RandomQuoteMapper
import com.example.quotes.data.repository.RandomQuoteRepository
import com.example.quotes.di.AppModule
import com.example.quotes.di.DatabaseModule
import com.example.quotes.remote.service.RandomQuoteApi
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
object MapperTestBase {
    val context = Mockito.mock(Context::class.java)
    var provideAppDatabase = Mockito.spy(DatabaseModule.provideAppDatabase(context))
    val randomQuoteDao = Mockito.mock(RandomQuoteDao::class.java)
    var retrofit: Retrofit = Mockito.spy(AppModule.provideQuotableServerRetrofit())
    var randomQuoteApi: RandomQuoteApi = Mockito.spy(retrofit.create(RandomQuoteApi::class.java))
    var randomQuoteMapper: RandomQuoteMapper = Mockito.spy(RandomQuoteMapper())
    var randomQuoteRepository: RandomQuoteRepository = Mockito.spy(RandomQuoteRepository(randomQuoteApi, randomQuoteDao,randomQuoteMapper))
    var testDataRandomQuoteDTO :RandomQuoteDTO? = RandomQuoteDTO("", "", "", "", "", "", 10, listOf())
}