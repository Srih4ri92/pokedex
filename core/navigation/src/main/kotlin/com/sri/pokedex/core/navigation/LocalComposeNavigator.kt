package com.sri.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val LocalComposeNavigator: ProvidableCompositionLocal<AppComposeNavigator> =
        compositionLocalOf {
            error(
                "No AppComposeNavigator provided! " +
                        "Make sure to wrap all usages of Pokedex components in PokedexTheme.",
            )
        }

val currentComposeNavigator: AppComposeNavigator
    @Composable
    @ReadOnlyComposable
    get() = LocalComposeNavigator.current
