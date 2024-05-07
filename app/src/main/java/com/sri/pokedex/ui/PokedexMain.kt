package com.sri.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.sri.pokedex.core.designsystem.theme.PokedexTheme
import com.sri.pokedex.core.navigation.AppComposeNavigator
import com.sri.pokedex.navigation.PokedexNavHost


@Composable
internal fun PokedexMain(composeNavigator: AppComposeNavigator) {

    PokedexTheme {
        val navController = rememberNavController()

        LaunchedEffect(key1 = Unit) {
            composeNavigator.handleNavigationCommands(navController)
        }

        PokedexNavHost(navController = navController)
    }

}