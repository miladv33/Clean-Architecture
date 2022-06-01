package com.example.takehomeassesmenttestnumber1.data.model

/**
 *
 * @property _id String
 * @property author String
 * @property authorSlug String
 * @property content String
 * @property dateAdded String
 * @property dateModified String
 * @property length Int
 * @property tags List<String>
 * @constructor
 */
data class RandomQuote(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int = -1,
    val tags: List<String>,
)