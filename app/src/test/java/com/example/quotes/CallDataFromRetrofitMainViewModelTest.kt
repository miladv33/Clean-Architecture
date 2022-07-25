package com.example.quotes

import app.cash.turbine.test
import com.example.quotes.base.MainViewModelTestBase.mainUseCase
import com.example.quotes.base.MainViewModelTestBase.mainViewModel
import com.example.quotes.base.MainViewModelTestBase.testDataRandomQuote
import com.example.quotes.base.MapperTestBase.randomQuoteApi
import com.example.quotes.base.MapperTestBase.randomQuoteMapper
import com.example.quotes.base.MapperTestBase.randomQuoteRepository
import com.example.quotes.base.MapperTestBase.testDataRandomQuoteDTO
import com.example.quotes.base.TestBase
import com.example.quotes.data.enum.Error
import com.example.quotes.data.model.CustomException
import com.example.quotes.data.model.RandomQuote
import com.google.common.truth.Truth.assertThat
import com.jraska.livedata.TestObserver
import org.junit.Test
import org.mockito.Mockito.*

class CallDataFromRetrofitMainViewModelTest : TestBase() {



    @Test
    fun `return api call as success Result`() = runJob {
        //act
        val randomFlow = randomQuoteRepository.getFromServer()
        //assert
        assertThat(randomFlow.isSuccess).isEqualTo(true)
        assertThat(randomFlow.getOrNull()?.content).isNotEmpty()
    }


    @Test
    fun `return api call as failure Result`() = runJob {
        //arrange
        val throwable = CustomException(Error.NullObject)
        val result = Result.failure<RandomQuote>(throwable)
        `when`(randomQuoteMapper.map(testDataRandomQuoteDTO)).thenReturn(result)
        //act
        mainViewModel.getQuotesFlow()
        //assert
        mainUseCase.executeQuotesFlow().test {
            val item = awaitItem()
            assertThat(item.isFailure).isTrue()
            assertThat(item.exceptionOrNull()).isEqualTo(throwable)
            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `change dialog visibility`() {
        mainViewModel.hideDialog()
        TestObserver.test(mainViewModel.showErrorDialogLiveData)
            .assertValue(false)
    }
}