package com.sri.pokedex.core.test

import com.sri.pokedex.core.model.Pokemon

object MockUtil {

    fun mockPokemon() = Pokemon(
        page = 0,
        nameField = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
    )

    fun mockPokemonList() = listOf(mockPokemon())

}