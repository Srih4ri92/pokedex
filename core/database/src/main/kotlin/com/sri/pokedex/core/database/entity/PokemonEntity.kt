package com.sri.pokedex.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemon"
)
data class PokemonEntity(
    val page:Int = 0,
    @PrimaryKey val name: String,
    val url: String
)
