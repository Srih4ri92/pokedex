package com.sri.pokedex.core.network.model

data class PokemonErrorResponse(
    val code: Int,
    val message: String?
)
