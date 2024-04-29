package com.sri.pokedex.core.database

import com.sri.pokedex.core.database.entity.mapper.asEntity
import com.sri.pokedex.core.test.MockUtil.mockPokemon
import com.sri.pokedex.core.test.MockUtil.mockPokemonList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class PokemonDaoTest: LocalDatabase() {

    private lateinit var pokemonDao: PokemonDao

    @Before
    fun init(){
        pokemonDao = db.pokemonDao()
    }

    @Test
    fun insertAndLoadPokemonList() = runBlocking{
        val mockDataList = mockPokemonList().asEntity()
        pokemonDao.insertPokemonList(mockDataList)

        val loadFromDB = pokemonDao.getPokemonList(page = 0)
        MatcherAssert.assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = listOf(mockPokemon()).asEntity()[0]
        MatcherAssert.assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))

    }

}