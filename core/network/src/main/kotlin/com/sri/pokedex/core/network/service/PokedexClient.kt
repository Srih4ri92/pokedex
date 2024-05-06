package com.sri.pokedex.core.network.service

import com.skydoves.sandwich.ApiResponse
import com.sri.pokedex.core.model.PokemonInfo
import com.sri.pokedex.core.network.model.PokemonResponse

class PokedexClient(
    private val pokedexService: PokedexService
) {

    suspend fun fetchPokemonList(
        page: Int
    ): ApiResponse<PokemonResponse> =
        pokedexService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE,
        )

    suspend fun fetchPokemonDetail(
        name: String
    ): ApiResponse<PokemonInfo> =
        pokedexService.fetchPokemonDetail(
            name = name
        )

    companion object {
        private const val PAGING_SIZE: Int = 20
    }
}
