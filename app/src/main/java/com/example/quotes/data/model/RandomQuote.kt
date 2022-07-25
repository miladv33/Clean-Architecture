package com.example.quotes.data.model

/**
 *
 * @property _id String
 * @property author String
 * @property authorSlug String
 * @property content String
 * @property date Date?
 * @property length Int
 * @property tags Tags?
 * @property id Int
 * @constructor
 */
data class RandomQuote(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val date:Date? = null,
    val length: Int = -1,
    val tags: Tags? = null,
    val id: Int = 0
):Model(){
    data class Tags(
        val tags: List<String>
    )
    data class Date(
        val dateAdded: String,
        val dateModified: String,
    )
}