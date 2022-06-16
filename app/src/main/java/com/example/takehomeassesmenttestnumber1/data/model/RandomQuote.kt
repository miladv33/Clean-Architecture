package com.example.takehomeassesmenttestnumber1.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class RandomQuote(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int = -1,
    val tags: List<String>,
    val id: Int = 0
)