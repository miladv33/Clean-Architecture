package com.example.takehomeassesmenttestnumber1

import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainUseCase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.mainViewModel
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.testDataRandomQuote
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteDao
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteMapper
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.randomQuoteRepository
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.testDataRandomQuoteDTO
import com.example.takehomeassesmenttestnumber1.base.TestBase
import org.junit.Test
import org.mockito.Mockito
import org.mockito.verification.VerificationMode

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