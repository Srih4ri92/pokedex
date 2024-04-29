package com.sri.pokedex.core.data.repository.home

import com.sri.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<Pokemon>>
}