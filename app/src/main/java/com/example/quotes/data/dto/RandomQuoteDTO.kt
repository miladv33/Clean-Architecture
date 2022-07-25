package com.example.quotes.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Random quote d t o
 *
 * @property _id
 * @property author
 * @property authorSlug
 * @property content
 * @property dateAdded
 * @property dateModified
 * @property length
 * @property tags
 * @property id
 * @constructor Create empty Random quote d t o
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