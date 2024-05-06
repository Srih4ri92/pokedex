package com.sri.pokedex.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.sri.pokedex.core.model.PokemonInfo
import javax.inject.Inject

@ProvidedTypeConverter
class StatsResponseConverter @Inject constructor(
    private val moshi: Moshi
) {
    @TypeConverter
    fun fromString(value: String): List<PokemonInfo.StatsResponse> {
       val listType = Types.newParameterizedType(List::class.java, PokemonInfo.StatsResponse::class.java)
        val adapter = moshi.adapter<List<PokemonInfo.StatsResponse>>(listType)
        return adapter.fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromList(list: List<PokemonInfo.StatsResponse>): String {
        val type = Types.newParameterizedType(List::class.java, PokemonInfo.StatsResponse::class.java)
        val adapter = moshi.adapter<List<PokemonInfo.StatsResponse>>(type)
        return adapter.toJson(list)
    }

}