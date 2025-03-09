package com.example.domain.di

import com.example.domain.usecase.DefaultFetchNewsUseCase
import com.example.domain.usecase.FetchNewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindFetchNewsUseCase(defaultFetchNewsUseCase: DefaultFetchNewsUseCase): FetchNewsUseCase
}