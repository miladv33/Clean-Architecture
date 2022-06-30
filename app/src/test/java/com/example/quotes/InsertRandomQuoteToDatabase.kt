package com.example.quotes

import com.example.quotes.base.MainViewModelTestBase.mainUseCase
import com.example.quotes.base.MainViewModelTestBase.mainViewModel
import com.example.quotes.base.MainViewModelTestBase.testDataRandomQuote
import com.example.quotes.base.MapperTestBase.randomQuoteDao
import com.example.quotes.base.MapperTestBase.randomQuoteMapper
import com.example.quotes.base.MapperTestBase.randomQuoteRepository
import com.example.quotes.base.MapperTestBase.testDataRandomQuoteDTO
import com.example.quotes.base.TestBase
import org.junit.Test
import org.mockito.Mockito

class InsertRandomQuoteToDatabase : TestBase() {

    @Test
    fun `insert flow from viewModel to room is ok`() = runJob {
        Mockito.`when`(randomQuoteMapper.convertModelToDTO(testDataRandomQuote)).thenReturn(testDataRandomQuoteDTO)
        val convertModelToDTO = randomQuoteMapper.convertModelToDTO(testDataRandomQuote)
        Mockito.`when`(convertModelToDTO?.let { randomQuoteDao.insert(it) }).thenReturn(Unit)
        mainViewModel.insertRandomQuote(testDataRandomQuote)
        Mockito.verify(mainUseCase).executeRandomQuoteInsert(testDataRandomQuote)
        Mockito.verify(randomQuoteRepository).insertToDatabase(testDataRandomQuote)
        convertModelToDTO?.let { Mockito.verify(randomQuoteDao).insert(it) }
    }

}