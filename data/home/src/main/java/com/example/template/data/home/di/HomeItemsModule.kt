package com.example.template.data.home.di

import com.example.template.data.home.HomeItemsRepositoryImpl
import com.example.template.domain.home.HomeItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class HomeItemsModule {
    @Singleton
    @Binds
    abstract fun bindsHomeItemsRepository(
        homeItemsRepository: HomeItemsRepositoryImpl,
    ): HomeItemsRepository
}
