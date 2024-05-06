package com.sri.pokedex.core.database.entity.mapper

import com.sri.pokedex.core.database.entity.PokemonInfoEntity
import com.sri.pokedex.core.model.PokemonInfo

object PokemonInfoEntityMapper : EntityMapper<PokemonInfo, PokemonInfoEntity>{
    override fun asEntity(domain: PokemonInfo): PokemonInfoEntity {
        return PokemonInfoEntity(
            id = domain.id,
            name = domain.name,
            height = domain.height,
            weight = domain.weight,
            stats = domain.stats,
            types = domain.types,
            experience = domain.experience,
            exp = domain.exp
        )
    }

    override fun asDomain(entity: PokemonInfoEntity): PokemonInfo {
        return PokemonInfo(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            stats = entity.stats,
            types = entity.types,
            experience = entity.experience,
            exp = entity.exp
        )
    }
}

fun PokemonInfo.asEntity(): PokemonInfoEntity = PokemonInfoEntityMapper.asEntity(this)

fun PokemonInfoEntity.asDomain(): PokemonInfo = PokemonInfoEntityMapper.asDomain(this)