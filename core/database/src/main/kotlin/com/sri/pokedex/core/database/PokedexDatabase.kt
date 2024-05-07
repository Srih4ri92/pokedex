package com.sri.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sri.pokedex.core.database.entity.PokemonEntity
import com.sri.pokedex.core.database.entity.PokemonInfoEntity

@Database(
    entities = [PokemonEntity::class,PokemonInfoEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(StatsResponseConverter::class,TypeResponseConverter::class)
abstract class PokedexDatabase :RoomDatabase(){

    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonInfoDao(): PokemonInfoDao
}