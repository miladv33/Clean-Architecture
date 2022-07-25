/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.quotes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotes.data.dto.RandomQuoteDTO

/**
 * Random quote dao
 *
 * @constructor Create empty Random quote dao
 */
@Dao
interface RandomQuoteDao {
    /**
     * Get random quotes
     *
     * @return
     */
    @Query("SELECT * FROM randomQuote ORDER BY dateAdded LIMIT 1")
    fun getRandomQuotes(): RandomQuoteDTO

    /**
     * Get random quotes
     *
     * @param plantId
     * @return
     */
    @Query("SELECT * FROM randomQuote WHERE id = :plantId")
    fun getRandomQuotes(plantId: String): RandomQuoteDTO

    /**
     * Insert all
     *
     * @param plants
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<RandomQuoteDTO>)

    /**
     * Insert
     *
     * @param randomQuoteDTO
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(randomQuoteDTO: RandomQuoteDTO)
}
