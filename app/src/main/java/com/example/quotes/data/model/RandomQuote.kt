package com.example.quotes.data.model

data class RandomQuote(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val date:Date? = null,
    val length: Int = -1,
    val tags: Tags? = null,
    val id: Int = 0
){
    data class Tags(
        val tags: List<String>
    )
    data class Date(
        val dateAdded: String,
        val dateModified: String,
    )
}