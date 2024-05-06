package com.sri.pokedex.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sri.pokedex.core.model.PokemonInfo

@Entity(
    tableName = "pokemon_info"
)
data class PokemonInfoEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val stats: List<PokemonInfo.StatsResponse>,
    val types: List<PokemonInfo.TypesResponse>,
    val exp: Int
)