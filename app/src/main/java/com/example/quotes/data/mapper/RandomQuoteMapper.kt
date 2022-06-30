package com.example.quotes.data.mapper

import com.example.quotes.data.dto.RandomQuoteDTO
import com.example.quotes.data.enum.Error
import com.example.quotes.data.model.CustomException
import com.example.quotes.data.model.RandomQuote

import kotlin.Exception

class RandomQuoteMapper : Mapper<RandomQuoteDTO?, RandomQuote> {
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

    override fun convertModelToDTO(model: RandomQuote): RandomQuoteDTO? {
        val randomQuoteDTO = with(model) {
            RandomQuoteDTO(
                _id = _id,
                author = author,
                authorSlug = authorSlug,
                content = content,
                dateAdded = date?.dateAdded ?: "",
                dateModified = date?.dateModified ?: "",
                length = length,
                tags = tags?.tags ?: arrayListOf()
            )
        }
        return randomQuoteDTO
    }

}