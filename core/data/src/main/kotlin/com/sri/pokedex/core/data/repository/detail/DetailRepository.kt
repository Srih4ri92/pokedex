package com.sri.pokedex.core.data.repository.detail

import com.sri.pokedex.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun fetchPokemonInfo(
        name: String,
        onComplete: () ->Unit,
        onError: (String?) -> Unit
    ): Flow<PokemonInfo>

}