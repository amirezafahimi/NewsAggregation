package com.example.feature.screen.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Article
import com.example.domain.usecase.FetchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    fetchNewsUseCase: FetchNewsUseCase
) : ViewModel() {
    val newsFlow: Flow<PagingData<Article>> =
        fetchNewsUseCase()
            .cachedIn(viewModelScope)
}