package com.sri.pokedex.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.sri.pokedex.core.model.PokemonInfo
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
){

    @TypeConverter
    fun fromString(value: String):List<PokemonInfo.TypesResponse> {
        val listType =
            Types.newParameterizedType(List::class.java, PokemonInfo.TypesResponse::class.java)
        val adapter = moshi.adapter<List<PokemonInfo.TypesResponse>>(listType)
        return adapter.fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromList(list: List<PokemonInfo.TypesResponse>): String {
        val listType =
            Types.newParameterizedType(List::class.java, PokemonInfo.TypesResponse::class.java)
        val adapter = moshi.adapter<List<PokemonInfo.TypesResponse>>(listType)
        return adapter.toJson(list)
    }
}