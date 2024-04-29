package com.sri.pokedex.core.network.service

import com.skydoves.sandwich.ApiResponse
import com.sri.pokedex.core.network.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ):ApiResponse<PokemonResponse>
}