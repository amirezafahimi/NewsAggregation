package com.example.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.domain.model.Article
import com.example.domain.mapper.toArticle
import com.example.domain.util.NewsQueries
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DefaultFetchNewsUseCase @Inject constructor(
    private val newsRepository: com.example.data.repository.NewsRepository
): FetchNewsUseCase {
    private val fromDate = getDateDaysAgo(3)
    private val toDate = getDateDaysAgo(0)

    override fun invoke(): Flow<PagingData<Article>> =
        newsRepository.fetchNews(NewsQueries.DEFAULT_QUERIES, fromDate, toDate).map { pagingData ->
            pagingData.map { articleDto ->
                articleDto.toArticle()
            }
        }
}

fun getDateDaysAgo(daysAgo: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(calendar.time)
}
