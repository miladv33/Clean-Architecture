package com.example.quotes.ui.secondpage

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.Pager
import androidx.paging.PagingConfig

@Composable
fun SecondPage() {
    History()
}

@Composable
fun History() {
    Text(text = "second page")
}