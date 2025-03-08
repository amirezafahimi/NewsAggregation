package com.example.data.ui

import com.example.data.BuildConfig
import com.example.data.remote.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if(BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(
        okHttpClient: OkHttpClient
    ) : NewsApiService {
        return Retrofit.Builder()
            .baseUrl(NewsApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

}