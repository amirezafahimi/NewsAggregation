package com.example.data.repository

import androidx.paging.PagingData
import com.example.data.model.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun fetchNews(queries: List<String>, from: String, to: String): Flow<PagingData<ArticleDto>>
}