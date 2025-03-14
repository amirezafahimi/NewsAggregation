package com.example.newsaggregation.di

import com.example.data.repository.DefaultNewsRepository
import com.example.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNewsRepository(defaultNewsRepository: DefaultNewsRepository): NewsRepository
}