package com.example.takehomeassesmenttestnumber1.data.model

import com.example.takehomeassesmenttestnumber1.data.enum.Error

data class CustomException(
    val messageBody: String,
    val error:Error
    ):Exception(messageBody) {
}