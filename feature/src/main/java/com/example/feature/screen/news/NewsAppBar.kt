package com.example.feature.screen.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature.R

@Composable
fun NewsAppBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 36.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(30.dp)
                    .width(30.dp),
                painter = painterResource(id = R.drawable.ic_blank),
                contentDescription = null
            )

            Text(
                text = "ALL NEWS",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )

        }

        Box(
            modifier = Modifier
                .padding(top = 4.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Black)

        )
    }

}

