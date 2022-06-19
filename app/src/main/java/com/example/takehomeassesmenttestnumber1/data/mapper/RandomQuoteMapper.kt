package com.example.takehomeassesmenttestnumber1.data.mapper

import com.example.takehomeassesmenttestnumber1.data.dto.RandomQuoteDTO
import com.example.takehomeassesmenttestnumber1.data.enum.Error
import com.example.takehomeassesmenttestnumber1.data.model.CustomException
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote

import kotlin.Exception

class RandomQuoteMapper : Mapper<RandomQuoteDTO?, Result<RandomQuote>> {
    override fun map(input: RandomQuoteDTO?): Result<RandomQuote> {
        return if (input != null) {
            var randomQuote: RandomQuote
            with(input) {
              randomQuote = RandomQuote(
                    _id, author, authorSlug, content,
                    date = RandomQuote.Date(dateAdded, dateModified),
                    length,
                    RandomQuote.Tags(tags), id
                )
            }
            Result.success(randomQuote)
        } else {
            mapFailure(CustomException(Error.NullObject))
        }
    }

    override fun mapFailure(exception: Exception): Result<RandomQuote> {
        return Result.failure(exception)
    }
}