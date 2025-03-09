package com.example.feature.screen.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.Article

@Composable
fun NewsScreen(
    onNavigateToDetail: (Article) -> Unit,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {

    val news = newsViewModel.newsFlow.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            NewsAppBar()
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                ListContent(
                    news = news,
                    onNavigateToDetail = onNavigateToDetail
                )
            }
        },
    )
}

@Composable
fun ListContent(
    news: LazyPagingItems<Article>,
    onNavigateToDetail: (Article) -> Unit,
) {
    val result = handlePagingResult(news = news)

    if (result) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                count = news.itemCount,
                key = { index -> news[index]?.url + index } // Ensure a unique, non-null key
            ) { index ->
                val newsItem = news[index]
                newsItem?.let {
                    NewsItem(news = it) {
                        onNavigateToDetail(it)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(1.dp)
                            .background(Black)
                    )
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(
    news: LazyPagingItems<Article>,
): Boolean {

    news.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                Shimmer()
                false
            }
            error != null -> {
                false
            }
            news.itemCount < 1 -> {
                false
            }
            else -> true
        }
    }
}


@Composable
fun NewsItem(
    news: Article,
    onNewsItemClick: () -> Unit
) {

    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 1f)
            .height(120.dp)
            .clickable { onNewsItemClick() },
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(8.dp),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = news.urlToImage)
                .build(),
            colorFilter = ColorFilter.colorMatrix(matrix),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, end = 4.dp),
                text = "${news.query} - ${news.publishedAt}",
                maxLines = 2,
                lineHeight = 18.sp,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, end = 4.dp),
                text = news.title,
                maxLines = 2,
                lineHeight = 18.sp,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier = Modifier.padding(top = 6.dp, end = 4.dp),
                text = news.content,
                maxLines = 4,
                color = Black.copy(alpha = 0.7f),
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        }

    }

}