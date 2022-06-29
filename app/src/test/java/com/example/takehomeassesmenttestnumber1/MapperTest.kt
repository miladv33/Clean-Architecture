package com.example.takehomeassesmenttestnumber1

import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase
import com.example.takehomeassesmenttestnumber1.base.MainViewModelTestBase.testDataRandomQuote
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase
import com.example.takehomeassesmenttestnumber1.base.MapperTestBase.testDataRandomQuoteDTO
import com.example.takehomeassesmenttestnumber1.base.TestBase
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.Mockito


class MapperTest : TestBase() {

    @Test
    fun `map model to dto`() {
        val map = MapperTestBase.randomQuoteMapper.map(testDataRandomQuoteDTO)
        assertThat(map.isSuccess).isTrue()


    }

    @Test
    fun `get data from server`() = runJob {
        val randomFlow = MapperTestBase.randomQuoteRepository.getFromServer()
        //assert
        assertThat(randomFlow.isSuccess).isEqualTo(true)
    }


}