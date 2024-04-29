package com.sri.pokedex.core.network

import com.skydoves.sandwich.ApiResponse
import com.sri.pokedex.core.network.service.PokedexService
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test


class PokedexServiceTest: ApiAbstract<PokedexService>() {

    private lateinit var service: PokedexService

    @Before
    fun initService(){
        service = createService(PokedexService::class.java)
    }

    @Test
    fun fetchPokemonListFromNetworkTest() = runTest{
        enqueueResponse("/PokemonResponse.json")
        val response = service.fetchPokemonList()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        MatcherAssert.assertThat(responseBody.count, CoreMatchers.`is`(964))
        MatcherAssert.assertThat(responseBody.results[0].name, CoreMatchers.`is`("Bulbasaur"))
        MatcherAssert.assertThat(
            responseBody.results[0].url,
            CoreMatchers.`is`("https://pokeapi.co/api/v2/pokemon/1/")
        )
    }
}