package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.toArticle
import com.example.data.remote.NewsApiService
import com.example.domain.model.Article
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class SequentialNewsPagingSource(
    private val apiService: NewsApiService,
    private val queries: List<String>,
    private val from: String,
    private val to: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1  // Default to page 1

            // Fetch articles asynchronously for each query
            val articlesByQuery: List<List<Article>> = coroutineScope {
                queries.map { query ->
                    async {
                        apiService.searchNews(
                            query = query,
                            from = from,
                            to = to,
                            page = page,
                            pageSize = params.loadSize
                        ).articles.map { it.toArticle().copy(query = query) }
                    }
                }.awaitAll()
            }

            // Interleave results to maintain the sequence pattern
            val maxSize = articlesByQuery.maxOfOrNull { it.size } ?: 0
            val interleavedArticles = mutableListOf<Article>()

            for (i in 0 until maxSize) {
                for (articles in articlesByQuery) {
                    if (i < articles.size) {
                        interleavedArticles.add(articles[i])
                    }
                }
            }

            LoadResult.Page(
                data = interleavedArticles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (interleavedArticles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}