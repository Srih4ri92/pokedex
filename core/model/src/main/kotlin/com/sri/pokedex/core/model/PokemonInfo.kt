package com.sri.pokedex.core.model

import androidx.compose.runtime.Immutable
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import kotlin.random.Random

@Immutable
@JsonClass(generateAdapter = true)
data class PokemonInfo(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "weight")
    val weight: Int,
    @field:Json(name = "base_experience")
    val experience: Int,
    @field:Json(name = "stats")
    val stats: List<StatsResponse>,
    @field:Json(name = "types")
    val types: List<TypesResponse>,
    val exp: Int = Random.nextInt(MAX_EXP)
) {

    val hp: Int by lazy {
        stats.firstOrNull { it.stat.name == "hp" }?.baseStat ?: Random.nextInt(MAX_HP)
    }
    val attack: Int by lazy {
        stats.firstOrNull { it.stat.name == "attack" }?.baseStat ?: Random.nextInt(MAX_ATTACK)
    }
    val defense: Int by lazy {
        stats.firstOrNull { it.stat.name == "defense" }?.baseStat ?: Random.nextInt(MAX_DEFENSE)
    }
    val speed: Int by lazy {
        stats.firstOrNull { it.stat.name == "speed" }?.baseStat ?: Random.nextInt(MAX_SPEED)
    }

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = " $hp/$MAX_HP"
    fun getAttackString(): String = " $attack/$MAX_ATTACK"
    fun getDefenseString(): String = " $defense/$MAX_DEFENSE"
    fun getSpeedString(): String = " $speed/$MAX_SPEED"
    fun getExpString(): String = " $exp/$MAX_EXP"

    @JsonClass(generateAdapter = true)
    data class TypesResponse(
        @field:Json(name = "slot")
        val slot: Int,
        @field:Json(name = "type")
        val type: Type,
    )

    @JsonClass(generateAdapter = true)
    class Type(
        @field:Json(name = "name")
        val name: String
    )

    @JsonClass(generateAdapter = true)
    data class StatsResponse(
        @field:Json(name = "base_stat")
        val baseStat: Int,
        @field:Json(name = "effort")
        val effort: Int,
        @field:Json(name = "stat")
        val stat: Stat,
    )

    @JsonClass(generateAdapter = true)
    data class Stat(
        @field:Json(name = "name")
        val name: String
    )

    companion object{
        const val MAX_HP = 300
        const val MAX_ATTACK = 300
        const val MAX_DEFENSE = 300
        const val MAX_SPEED = 300
        const val MAX_EXP = 1000
    }

}
