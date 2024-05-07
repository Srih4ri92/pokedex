package com.sri.pokedex.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sri.pokedex.core.navigation.PokedexScreens


@Composable
internal fun PokedexNavHost(
    navController: NavHostController,
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = PokedexScreens.Home.name
        ) {
            pokemonNavigation()
        }
    }
}