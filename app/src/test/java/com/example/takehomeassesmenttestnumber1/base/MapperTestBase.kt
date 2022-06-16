package com.example.takehomeassesmenttestnumber1.base

import android.content.Context
import com.example.takehomeassesmenttestnumber1.data.dto.RandomQuoteDTO
import com.example.takehomeassesmenttestnumber1.data.mapper.RandomQuoteMapper
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import com.example.takehomeassesmenttestnumber1.data.repository.RandomQuoteRepository
import com.example.takehomeassesmenttestnumber1.di.AppModule
import com.example.takehomeassesmenttestnumber1.di.DatabaseModule
import com.example.takehomeassesmenttestnumber1.remote.service.RandomQuoteApi
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
    val randomQuoteDao = Mockito.spy(DatabaseModule.provideRandomQuoteDao(provideAppDatabase))
    var retrofit: Retrofit = Mockito.spy(AppModule.provideQuotableServerRetrofit())
    var randomQuoteApi: RandomQuoteApi = Mockito.spy(retrofit.create(RandomQuoteApi::class.java))
    var randomQuoteMapper: RandomQuoteMapper = Mockito.spy(RandomQuoteMapper::class.java)
    var randomQuoteRepository: RandomQuoteRepository = Mockito.spy(RandomQuoteRepository(randomQuoteApi, randomQuoteDao,randomQuoteMapper))
    var testDataRandomQuoteDTO :RandomQuoteDTO? = RandomQuoteDTO("", "", "", "", "", "", 10, listOf())
}