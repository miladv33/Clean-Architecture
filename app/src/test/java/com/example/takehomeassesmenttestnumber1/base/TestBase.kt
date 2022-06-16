package com.example.takehomeassesmenttestnumber1.base

import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
open class TestBase {
    /**
     * Dispatcher for test coroutine we need to use a testDispatcher
     */
    val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    fun runJob(unit: suspend () -> Unit) = runBlocking {
        val job = launch {
            unit.invoke()
        }
        job.join()
        job.cancel()
    }
}