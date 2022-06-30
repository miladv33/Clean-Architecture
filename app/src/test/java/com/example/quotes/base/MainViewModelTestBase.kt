package com.example.quotes.base

import com.example.quotes.base.MapperTestBase.randomQuoteRepository
import com.example.quotes.data.model.RandomQuote
import com.example.quotes.domain.usecase.MainUseCase
import com.example.quotes.presentation.viewmodel.MainViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
object MainViewModelTestBase : TestBase() {
    var mainUseCase: MainUseCase = Mockito.spy(MainUseCase(randomQuoteRepository))
    var mainViewModel: MainViewModel = Mockito.spy(MainViewModel(mainUseCase))
    var testDataRandomQuote = RandomQuote("", "", "", "", date = RandomQuote.Date("",""), length = -1, tags = RandomQuote.Tags(arrayListOf()))

}


