package com.sri.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sri.pokedex.core.database.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PokedexDatabase :RoomDatabase(){

    abstract fun pokemonDao(): PokemonDao
}