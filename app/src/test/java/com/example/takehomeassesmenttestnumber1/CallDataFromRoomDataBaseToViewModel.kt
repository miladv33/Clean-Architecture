package com.example.takehomeassesmenttestnumber1

import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainShowCase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainViewModel
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.testDataRandomQuote
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteDao
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteRepository
import com.example.takehomeassesmenttestnumber1.base.TestBase
import com.example.takehomeassesmenttestnumber1.data.model.test
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify


class CallDataFromRoomDataBaseToViewModel : TestBase() {

    @Test
    fun `flow of data is working`() = runJob {
        mainViewModel.getQuotesFlow()
        verify(mainShowCase).executeQuotesFlow()
        verify(randomQuoteRepository).getOffline()
        verify(randomQuoteDao).getRandomQuotes()
    }

    @Test
    fun `return a Kotlin Result from Room`() = runJob {
        doReturn(Result.success(testDataRandomQuote)).`when`(randomQuoteRepository).getOffline()
        assertThat(randomQuoteRepository.getOffline()).isEqualTo(Result.success(testDataRandomQuote))
    }


}
