package com.example.takehomeassesmenttestnumber1

import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainShowCase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainViewModel
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteDao
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteRepository
import com.example.takehomeassesmenttestnumber1.base.TestBase
import org.junit.Test
import org.mockito.Mockito.verify


class CallDataFromRoomDataBaseToViewModel : TestBase() {

    @Test
    fun `flow of data is working`() {
        mainViewModel.getQuotesFlow()
        verify(mainShowCase).executeQuotesFlow()
        verify(randomQuoteRepository).getOfflineRandomFlow()
        verify(randomQuoteDao).getRandomQuotes()
    }

    @Test
    fun `return a Kotlin Result from Room`() {

    }


}
