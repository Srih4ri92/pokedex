package com.sri.pokedex.core.data.di

import com.sri.pokedex.core.data.repository.detail.DetailRepository
import com.sri.pokedex.core.data.repository.detail.DetailRepositoryImpl
import com.sri.pokedex.core.data.repository.home.HomeRepository
import com.sri.pokedex.core.data.repository.home.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun bindsDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}