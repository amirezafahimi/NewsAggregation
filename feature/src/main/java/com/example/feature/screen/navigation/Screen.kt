package com.example.feature.screen.navigation

sealed class Screen(val route:String) {
    data object News : Screen("news_screen")
    data object Detail : Screen("detail_screen")
}