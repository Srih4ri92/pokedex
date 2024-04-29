package com.sri.pokedex.core.data.repository.home

import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import com.sri.pokedex.core.database.PokemonDao
import com.sri.pokedex.core.database.entity.mapper.asDomain
import com.sri.pokedex.core.database.entity.mapper.asEntity
import com.sri.pokedex.core.model.Pokemon
import com.sri.pokedex.core.network.Dispatcher
import com.sri.pokedex.core.network.PokedexAppDispatchers
import com.sri.pokedex.core.network.service.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class HomeRepositoryImpl(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao,
    @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): HomeRepository {
    override suspend fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var pokemons = pokemonDao.getPokemonList(page).asDomain()

        if(pokemons.isEmpty()){
            val response = pokedexClient.fetchPokemonList(page)
            response.suspendOnSuccess {
                pokemons = data.results
                pokemons.map { pokemon -> pokemon.page = page }
                pokemonDao.insertPokemonList(pokemonList = pokemons.asEntity())
                emit(pokemonDao.getAllPokemonList(page).asDomain())
            }.onFailure {
                onError(message())
            }
        }else{
            emit(pokemonDao.getAllPokemonList(page).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}