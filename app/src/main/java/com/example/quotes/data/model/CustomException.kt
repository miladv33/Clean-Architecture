package com.example.quotes.data.model

import com.example.quotes.data.enum.Error

/**
 *
 * deal with any kinds of exceptions
 * @property error Error
 * @constructor
 */
data class CustomException(
    val error:Error
    ):Exception() {
}