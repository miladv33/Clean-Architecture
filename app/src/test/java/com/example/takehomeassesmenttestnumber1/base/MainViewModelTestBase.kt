package com.example.takehomeassesmenttestnumber1.base

import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteRepository
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import com.example.takehomeassesmenttestnumber1.domain.usecase.MainUseCase
import com.example.takehomeassesmenttestnumber1.presentation.viewmodel.MainViewModel
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


