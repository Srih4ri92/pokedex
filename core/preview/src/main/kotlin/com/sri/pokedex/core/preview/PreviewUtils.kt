package com.sri.pokedex.core.preview

import com.sri.pokedex.core.model.Pokemon

object PreviewUtils{
    fun mockPokemon() = Pokemon(
        page = 0,
        nameField = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
    )

    fun mockPokemonList() = List(10) {
        Pokemon(page = 0, nameField = "bulbasaur$it", url = "")
    }

}