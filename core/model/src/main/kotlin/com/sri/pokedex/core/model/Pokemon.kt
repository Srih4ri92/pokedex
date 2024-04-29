package com.sri.pokedex.core.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
@JsonClass(generateAdapter = true)
data class Pokemon(
    var page: Int = 0,
    @field:Json(name = "name")
    val nameField: String,
    @field:Json(name = "url")
    val url: String
) : Parcelable {
    val name: String
        get() = nameField.replaceFirstChar { it.toUpperCase() }

    val imageUrl:String
        inline get() {
            val index = url.split("/".toRegex()).dropLast(1).last()
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                    "pokemon/other/official-artwork/$index.png"
        }
}
