package com.example.quotes

import com.example.quotes.base.MapperTestBase
import com.example.quotes.base.MapperTestBase.testDataRandomQuoteDTO
import com.example.quotes.base.TestBase
import com.google.common.truth.Truth.assertThat
import org.junit.Test


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