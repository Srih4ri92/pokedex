package com.sri.pokedex.core.database.entity.mapper

import com.sri.pokedex.core.database.entity.PokemonEntity
import com.sri.pokedex.core.model.Pokemon

object PokemonEntityMapper: EntityMapper<List<Pokemon>, List<PokemonEntity>>{
    override fun asEntity(domain: List<Pokemon>): List<PokemonEntity> {
        return domain.map { pokemon ->
            PokemonEntity(
                page = pokemon.page,
                name = pokemon.name,
                url = pokemon.url,
            )
        }
    }

    override fun asDomain(entity: List<PokemonEntity>): List<Pokemon> {
        return entity.map { pokemonEntity ->
            Pokemon(
                page = pokemonEntity.page,
                nameField = pokemonEntity.name,
                url = pokemonEntity.url,
            )
        }
    }

}

fun List<Pokemon>.asEntity(): List<PokemonEntity> {
    return PokemonEntityMapper.asEntity(this)
}

fun List<PokemonEntity>?.asDomain(): List<Pokemon> {
    return PokemonEntityMapper.asDomain(this.orEmpty())
}