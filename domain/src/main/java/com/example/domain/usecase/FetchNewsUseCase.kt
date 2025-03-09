package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface FetchNewsUseCase {
    operator fun invoke(): Flow<PagingData<Article>>
}