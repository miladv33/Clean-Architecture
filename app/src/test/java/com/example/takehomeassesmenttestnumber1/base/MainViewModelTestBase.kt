package com.example.takehomeassesmenttestnumber1.base

import android.content.Context
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteRepository
import com.example.takehomeassesmenttestnumber1.data.mapper.RandomQuoteMapper
import com.example.takehomeassesmenttestnumber1.di.AppModule
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import com.example.takehomeassesmenttestnumber1.data.repository.RandomQuoteRepository
import com.example.takehomeassesmenttestnumber1.di.DatabaseModule
import com.example.takehomeassesmenttestnumber1.remote.service.RandomQuoteApi
import com.example.takehomeassesmenttestnumber1.domain.usecase.MainUseCase
import com.example.takehomeassesmenttestnumber1.presentation.viewmodel.MainViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
object MainViewModelTestBase : TestBase() {
    var mainShowCase: MainUseCase = Mockito.spy(MainUseCase(randomQuoteRepository))
    var mainViewModel: MainViewModel = Mockito.spy(MainViewModel(mainShowCase))
    var testDataRandomQuote = RandomQuote("", "", "", "", "", "", 10, listOf())

}


