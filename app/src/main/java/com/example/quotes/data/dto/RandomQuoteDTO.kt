package com.example.quotes.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "randomQuote")
data class RandomQuoteDTO(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int = -1,
    val tags: List<String>,
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int = 0
    )