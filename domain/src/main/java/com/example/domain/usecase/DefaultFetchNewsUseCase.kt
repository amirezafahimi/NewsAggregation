package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import com.example.domain.util.NewsQueries
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DefaultFetchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
): FetchNewsUseCase {
    private val fromDate = getDateDaysAgo(3)
    private val toDate = getDateDaysAgo(0)

    override fun invoke(): Flow<PagingData<Article>> =
        newsRepository.fetchNews(NewsQueries.DEFAULT_QUERIES, fromDate, toDate)
}

fun getDateDaysAgo(daysAgo: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(calendar.time)
}
