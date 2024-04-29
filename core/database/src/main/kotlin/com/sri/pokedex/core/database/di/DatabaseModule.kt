package com.sri.pokedex.core.database.di

import android.app.Application
import androidx.room.Room
import com.sri.pokedex.core.database.PokedexDatabase
import com.sri.pokedex.core.database.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providePokedexDatabase(
        application: Application
    ): PokedexDatabase{
        return Room
            .databaseBuilder(
                application,
                PokedexDatabase::class.java,
                "Pokedex.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesPokemonDao(pokedexDatabase: PokedexDatabase): PokemonDao =
        pokedexDatabase.pokemonDao()
}