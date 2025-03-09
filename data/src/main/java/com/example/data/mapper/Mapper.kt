package com.example.data.mapper

import com.example.data.model.ArticleDto
import com.example.data.model.SourceDto
import com.example.domain.model.Article
import com.example.domain.model.Source


fun ArticleDto.toArticle(): Article =
    Article(
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt ?: "",
        source = source?.toSource() ?: Source("", ""),
        title = title ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        query = query ?: ""
    )

fun SourceDto.toSource(): Source =
    Source(
        id = id ?: "",
        name = name ?: ""
    )