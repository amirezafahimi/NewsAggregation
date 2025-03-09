package com.example.domain.util

object NewsQueries {
    val DEFAULT_QUERIES = listOf(
        NewsQuery.MICROSOFT.query,
        NewsQuery.APPLE.query,
        NewsQuery.GOOGLE.query,
        NewsQuery.TESLA.query
    )
}

enum class NewsQuery(val query: String) {
    MICROSOFT("Microsoft"),
    APPLE("Apple"),
    GOOGLE("Google"),
    TESLA("Tesla")
}