package com.sri.pokedex.core.data.repository.detail

import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.sri.pokedex.core.database.PokemonInfoDao
import com.sri.pokedex.core.database.entity.mapper.asDomain
import com.sri.pokedex.core.database.entity.mapper.asEntity
import com.sri.pokedex.core.network.Dispatcher
import com.sri.pokedex.core.network.PokedexAppDispatchers
import com.sri.pokedex.core.network.model.mapper.ErrorResponseMapper
import com.sri.pokedex.core.network.service.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val pokemonClient: PokedexClient,
    private val pokemonInfoDao: PokemonInfoDao,
    @Dispatcher(PokedexAppDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : DetailRepository {

    override suspend fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)

        if(pokemonInfo == null){
            val response = pokemonClient.fetchPokemonDetail(name)
            response.suspendOnSuccess {
                pokemonInfoDao.insertPokemonInfo(data.asEntity())
                emit(data)
            }.onError {
                map(ErrorResponseMapper) { onError("[Code: ${code}]: $message") }
            }.onException {
                onError(message)
            }
        }else{
            emit(pokemonInfo.asDomain())
        }
    }.onCompletion { onComplete() }.flowOn(dispatcher)

}