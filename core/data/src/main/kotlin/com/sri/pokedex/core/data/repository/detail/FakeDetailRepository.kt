package com.sri.pokedex.core.data.repository.detail

import com.sri.pokedex.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDetailRepository: DetailRepository {
    override suspend fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow<PokemonInfo> {}
}