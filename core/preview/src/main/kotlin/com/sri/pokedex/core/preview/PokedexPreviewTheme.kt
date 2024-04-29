package com.sri.pokedex.core.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sri.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun PokedexPreviewTheme(
    content:@Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit
) {

    CompositionLocalProvider {
        PokedexTheme {
            SharedTransitionScope {
                AnimatedVisibility(visible = true){
                    content(this)
                }
            }
        }
    }

}