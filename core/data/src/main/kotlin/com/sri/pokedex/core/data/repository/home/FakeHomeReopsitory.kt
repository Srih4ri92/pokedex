package com.sri.pokedex.core.data.repository.home

import com.sri.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeHomeReopsitory: HomeRepository {
    override suspend fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>> = flowOf()


}