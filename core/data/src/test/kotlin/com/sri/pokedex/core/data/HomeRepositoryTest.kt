package com.sri.pokedex.core.data

import app.cash.turbine.test
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import com.sri.pokedex.core.data.repository.home.HomeRepositoryImpl
import com.sri.pokedex.core.database.PokemonDao
import com.sri.pokedex.core.database.entity.mapper.asEntity
import com.sri.pokedex.core.network.model.PokemonResponse
import com.sri.pokedex.core.network.service.PokedexClient
import com.sri.pokedex.core.network.service.PokedexService
import com.sri.pokedex.core.test.MainCoroutinesRule
import com.sri.pokedex.core.test.MockUtil.mockPokemonList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class HomeRepositoryTest {

    private lateinit var repository: HomeRepositoryImpl
    private lateinit var client: PokedexClient
    private val service: PokedexService = mock()
    private val pokemonDao: PokemonDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        client = PokedexClient(service)
        repository = HomeRepositoryImpl(client, pokemonDao, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchPokemonListFromNetworkTest() = runTest {
        val mockData =
            PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
        whenever(pokemonDao.getPokemonList(page = 0)).thenReturn(emptyList())
        whenever(pokemonDao.getAllPokemonList(page = 0)).thenReturn(mockData.results.asEntity())
        whenever(service.fetchPokemonList()).thenReturn(
            ApiResponse.responseOf {
                Response.success(
                    mockData,
                )
            },
        )

        repository.fetchPokemonList(
            page = 0,
            onStart = {},
            onComplete = {},
            onError = {},
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val actualItem = awaitItem()[0]
            Assert.assertEquals(0, actualItem.page)
            // First letter of name is always upper case
            Assert.assertEquals("Bulbasaur", actualItem.name)
            Assert.assertEquals("https://pokeapi.co/api/v2/pokemon/1/", actualItem.url)
            awaitComplete()
        }

        verify(pokemonDao, atLeastOnce()).getPokemonList(page = 0)
        verify(service, atLeastOnce()).fetchPokemonList()
        verify(pokemonDao, atLeastOnce()).insertPokemonList(mockData.results.asEntity())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun fetchPokemonListFromDatabaseTest() = runTest {
        val mockData =
            PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
        whenever(pokemonDao.getPokemonList(page = 0)).thenReturn(mockData.results.asEntity())
        whenever(pokemonDao.getAllPokemonList(page = 0)).thenReturn(mockData.results.asEntity())

        repository.fetchPokemonList(
            page = 0,
            onStart = {},
            onComplete = {},
            onError = {},
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val actualItem = awaitItem()[0]
            Assert.assertEquals(0, actualItem.page)
            // First letter of name is always upper case
            Assert.assertEquals("Bulbasaur", actualItem.name)
            Assert.assertEquals("https://pokeapi.co/api/v2/pokemon/1/", actualItem.url)
            awaitComplete()
        }

        verify(pokemonDao, atLeastOnce()).getPokemonList(page = 0)
        verify(pokemonDao, atLeastOnce()).getAllPokemonList(page = 0)
    }
}