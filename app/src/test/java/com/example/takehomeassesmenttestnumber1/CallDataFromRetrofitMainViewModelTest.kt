package com.example.takehomeassesmenttestnumber1

import app.cash.turbine.test
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainShowCase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainViewModel
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.testDataRandomQuote
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteApi
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteRepository
import com.example.takehomeassesmenttestnumber1.base.TestBase
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import com.google.common.truth.Truth.assertThat
import com.jraska.livedata.TestObserver
import org.junit.Test
import org.mockito.Mockito.*

class CallDataFromRetrofitMainViewModelTest : TestBase() {


    @Test
    fun `verify the flow of data`() = runJob {
        //arrange
        val result = Result.success(testDataRandomQuote)
        doReturn(result).`when`(randomQuoteApi).getRandomQuote()
        //act
        mainViewModel.getQuotesFlow()
        //assert
        verify(mainShowCase).executeQuotesFlow()
        verify(randomQuoteRepository).getRandomFlow()
        verify(randomQuoteApi).getRandomQuote()
    }

    @Test
    fun `return api call as success Result`() = runJob {
        //arrange
        val result = Result.success(testDataRandomQuote)
        doReturn(result).`when`(randomQuoteApi).getRandomQuote()
        //act
        val randomFlow = randomQuoteRepository.getRandomFlow()
        //assert
        assertThat(randomFlow.isSuccess).isEqualTo(true)
        assertThat(randomFlow.getOrNull()).isEqualTo(testDataRandomQuote)
    }


    @Test
    fun `return api call as failure Result`() = runJob {
        //arrange
        val throwable = Throwable("Error", null)
        val result = Result.failure<RandomQuote>(throwable)
        doReturn(result).`when`(randomQuoteApi).getRandomQuote()
        //act
        mainViewModel.getQuotesFlow()
        //assert
        mainShowCase.executeQuotesFlow().test {
            val item = awaitItem()
            assertThat(item.isFailure).isTrue()
            assertThat(item.exceptionOrNull()).isEqualTo(throwable)
            cancelAndConsumeRemainingEvents()
        }
        TestObserver.test(mainViewModel.randomQuoteErrorLiveData)
            .assertHasValue()
            .assertValue(throwable)
    }

    @Test
    fun `change live data value `() = runJob {
        //arrange
        doReturn(testDataRandomQuote).`when`(randomQuoteApi).getRandomQuote()
        //act
        mainViewModel.getQuotesFlow()
        //assert
        TestObserver.test(mainViewModel.randomQuoteLiveData)
            .assertHasValue()
            .assertValue(testDataRandomQuote)
    }


    @Test
    fun `change dialog visibility`() {
        mainViewModel.hideDialog()
        TestObserver.test(mainViewModel.showErrorDialogLiveData)
            .assertValue(false)
    }
}