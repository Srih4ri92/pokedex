package com.sri.pokedex.core.database.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.sri.pokedex.core.database.PokedexDatabase
import com.sri.pokedex.core.database.PokemonDao
import com.sri.pokedex.core.database.PokemonInfoDao
import com.sri.pokedex.core.database.StatsResponseConverter
import com.sri.pokedex.core.database.TypeResponseConverter
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
        application: Application,
        typeResponseConverter: TypeResponseConverter,
        statsResponseConverter: StatsResponseConverter
    ): PokedexDatabase{
        return Room
            .databaseBuilder(
                application,
                PokedexDatabase::class.java,
                "Pokedex.db"
            )
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .addTypeConverter(statsResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providesPokemonDao(pokedexDatabase: PokedexDatabase): PokemonDao =
        pokedexDatabase.pokemonDao()

    @Provides
    @Singleton
    fun providesPokemonInfoDao(pokedexDatabase: PokedexDatabase): PokemonInfoDao =
        pokedexDatabase.pokemonInfoDao()

    @Provides
    @Singleton
    fun provideStatResponseConverter(moshi: Moshi): StatsResponseConverter = StatsResponseConverter(moshi)

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter = TypeResponseConverter(moshi)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

}