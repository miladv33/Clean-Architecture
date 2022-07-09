package com.example.quotes.data.mapper

import com.example.quotes.data.dto.RandomQuoteDTO
import com.example.quotes.data.mapper.base.Mapper
import com.example.quotes.data.model.RandomQuote

class RandomQuoteMapper : Mapper<RandomQuoteDTO, RandomQuote> {

    override fun convertModelToDTO(model: RandomQuote): RandomQuoteDTO {
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

    override fun createModelFromDTO(input: RandomQuoteDTO): RandomQuote {
        var randomQuote: RandomQuote
        with(input) {
            randomQuote = RandomQuote(
                _id, author, authorSlug, content,
                date = RandomQuote.Date(dateAdded, dateModified),
                length,
                RandomQuote.Tags(tags), id
            )
        }
        return randomQuote
    }
}