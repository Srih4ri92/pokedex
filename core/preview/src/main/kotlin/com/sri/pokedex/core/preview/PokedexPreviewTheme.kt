package com.sri.pokedex.core.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sri.pokedex.core.designsystem.theme.PokedexTheme
import com.sri.pokedex.core.navigation.LocalComposeNavigator
import com.sri.pokedex.core.navigation.PokedexComposeNavigator

@Composable
fun PokedexPreviewTheme(
    content:@Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit
) {

    CompositionLocalProvider(
        LocalComposeNavigator provides PokedexComposeNavigator()
    ) {
        PokedexTheme {
            SharedTransitionScope {
                AnimatedVisibility(visible = true){
                    content(this)
                }
            }
        }
    }

}