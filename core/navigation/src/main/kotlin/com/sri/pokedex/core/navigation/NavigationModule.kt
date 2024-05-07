package com.sri.pokedex.core.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun provideComposeNavigator(
        pokedexComposeNavigator: PokedexComposeNavigator
    ) : AppComposeNavigator
}