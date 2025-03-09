package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.model.ArticleDto
import com.example.data.paging.SequentialNewsPagingSource
import com.example.data.remote.NewsApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultNewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
): NewsRepository {
    override fun fetchNews(queries: List<String>, from: String, to: String): Flow<PagingData<ArticleDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SequentialNewsPagingSource(newsApiService, queries, from, to) }
        ).flow
    }
}