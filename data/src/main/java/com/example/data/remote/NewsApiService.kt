package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("everything")
    suspend fun searchNews(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse


}