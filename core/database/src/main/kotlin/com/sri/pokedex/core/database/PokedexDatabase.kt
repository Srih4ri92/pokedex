package com.sri.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sri.pokedex.core.database.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(StatsResponseConverter::class,TypeResponseConverter::class)
abstract class PokedexDatabase :RoomDatabase(){

    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonInfoDao(): PokemonInfoDao
}