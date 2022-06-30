package com.example.quotes.data.model

import com.example.quotes.data.enum.Error

data class CustomException(
    val error:Error
    ):Exception() {
}