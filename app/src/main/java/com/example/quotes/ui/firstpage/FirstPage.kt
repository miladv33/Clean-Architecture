package com.example.quotes.ui.firstpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quotes.presentation.viewmodel.MainViewModel

/**
 * Greeting
 *
 * @param viewModel
 */
@Composable
fun firstPage(viewModel: MainViewModel, navigate: () -> Unit) {
    val quote = viewModel.randomQuoteLiveData.observeAsState()
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = " From ${quote.value?.author ?: ""}: ")
        Text(modifier = Modifier.padding(start = 15.dp), text = quote.value?.content ?: "")
        Button(modifier = Modifier.padding(top = 15.dp), onClick = {

            viewModel.getQuotesFlow()
        }) {
            Text(text = "Another Quote")
        }

        Button(onClick = {
            navigate.invoke()
        }) {
            Text(text = "Second page")
        }
    }
}