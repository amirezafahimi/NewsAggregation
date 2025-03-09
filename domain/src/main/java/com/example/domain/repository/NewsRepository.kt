package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun fetchNews(queries: List<String>, from: String, to: String): Flow<PagingData<Article>>
}