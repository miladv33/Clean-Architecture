package com.example.quotes

import com.example.quotes.base.MainViewModelTestBase.mainUseCase
import com.example.quotes.base.MainViewModelTestBase.mainViewModel
import com.example.quotes.base.MainViewModelTestBase.testDataRandomQuote
import com.example.quotes.base.MapperTestBase.randomQuoteDao
import com.example.quotes.base.MapperTestBase.randomQuoteRepository
import com.example.quotes.base.TestBase
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify


class CallDataFromRoomDataBaseToViewModel : TestBase() {

    @Test
    fun `flow of data is working`() = runJob {
        mainViewModel.getQuotesFlow()
        verify(mainUseCase).executeQuotesFlow()
        verify(randomQuoteRepository).getOffline()
        verify(randomQuoteDao).getRandomQuotes()
    }

    @Test
    fun `data are returnable as a kotlin result`() = runJob {
        doReturn(Result.success(testDataRandomQuote)).`when`(randomQuoteRepository).getOffline()
        assertThat(randomQuoteRepository.getOffline()).isEqualTo(Result.success(testDataRandomQuote))
    }


}
