package com.sri.pokedex.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.sri.pokedex.core.model.Pokemon

sealed class PokedexScreens(
    route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {

    val name: String = route.appendArguments(navArguments)

    data object Home : PokedexScreens("home")

    data object Detail : PokedexScreens(
        route = "detail",
        navArguments = listOf(
            navArgument("pokemon") {
                type = PokemonType()
                nullable = false
            }
        )
    ) {
        fun createRoute(pokemon: Pokemon) =
            name.replace("{${navArguments.first().name}}", PokemonType.encodeToString(pokemon))
    }
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
    val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
        .orEmpty()
    val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
        .orEmpty()
    return "$this$mandatoryArguments$optionalArguments"
}