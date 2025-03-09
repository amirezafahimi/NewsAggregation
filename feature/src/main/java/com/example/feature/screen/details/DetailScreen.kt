package com.example.feature.screen.details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.Article


@Composable
fun DetailScreen(
    article: Article,
    onBackClick: () -> Unit
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Scaffold(
        topBar = {
            DetailAppBar {
                onBackClick()
            }
        },
    ) { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier.padding(paddingValues = paddingValues)
                .padding(16.dp)

        ) {

            Text(
                text = article.title,
                lineHeight = 28.sp,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = androidx.compose.ui.Modifier.padding(top = 8.dp),
                text = article.publishedAt,
                lineHeight = 18.sp,
                fontSize = 15.sp,
                color = Gray,
            )
            Text(
                modifier = androidx.compose.ui.Modifier.padding(top = 8.dp),
                text = article.author,
                color = Black,
                lineHeight = 18.sp,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            AsyncImage(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 8.dp),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = article.urlToImage)
                    .build(),
                colorFilter = ColorFilter.colorMatrix(matrix),
                contentDescription = null
            )
            Text(
                modifier = androidx.compose.ui.Modifier.padding(top = 8.dp),
                text = article.content,
                color = Black,
                lineHeight = 22.sp,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
