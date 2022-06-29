package com.example.takehomeassesmenttestnumber1

import app.cash.turbine.test
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainUseCase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainViewModel
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.testDataRandomQuote
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteApi
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteMapper
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteRepository
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.testDataRandomQuoteDTO
import com.example.takehomeassesmenttestnumber1.base.TestBase
import com.example.takehomeassesmenttestnumber1.data.enum.Error
import com.example.takehomeassesmenttestnumber1.data.model.CustomException
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import com.google.common.truth.Truth.assertThat
import com.jraska.livedata.TestObserver
import org.junit.Test
import org.mockito.Mockito
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
        verify(mainUseCase).executeQuotesFlow()
        verify(randomQuoteRepository).getFromServer()
        verify(randomQuoteApi).getRandomQuote()
    }

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