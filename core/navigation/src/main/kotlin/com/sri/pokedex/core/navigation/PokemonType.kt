package com.sri.pokedex.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.squareup.moshi.Moshi
import com.sri.pokedex.core.model.Pokemon

class PokemonType : NavType<Pokemon?>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Pokemon? = bundle.parcelable(key)

    override fun parseValue(value: String): Pokemon? {
        val decoded = Uri.decode(value)
        return pokemonAdapter.fromJson(decoded)!!
    }

    override fun put(bundle: Bundle, key: String, value: Pokemon?) =
        bundle.putParcelable(key, value)

    companion object {
        private val pokemonAdapter = Moshi.Builder().build().adapter(Pokemon::class.java)

        fun encodeToString(pokemon: Pokemon): String = Uri.encode(pokemonAdapter.toJson(pokemon))
    }
}