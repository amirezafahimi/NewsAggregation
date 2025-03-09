package com.example.feature.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.domain.model.Article
import com.example.feature.screen.details.DetailScreen
import com.example.feature.screen.news.NewsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.News.route
    ) {

        composable(route = Screen.Detail.route) {
            navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Article>("article")
                ?.let {
                    DetailScreen(article = it) {
                        navController.navigateUp()
                    }
                }

        }

        composable(route = Screen.News.route) {
            NewsScreen(
                onNavigateToDetail = { article ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "article",
                        value = article
                    )
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
    }

}