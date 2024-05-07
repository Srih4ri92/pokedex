package com.sri.pokedex.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.kmpalette.palette.graphics.Palette
import com.sri.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun Palette?.paletteBackgroundBrush(): State<Brush> {
    val defaultBackground = PokedexTheme.background.color
    return remember(this) {
        derivedStateOf {
            val lightSwatch = this?.lightMutedSwatch?.rgb
            val dominantSwatch = this?.dominantSwatch?.rgb

            if (dominantSwatch != null){
                val dominantColor = Color(dominantSwatch)
                if(lightSwatch != null){
                    val lightColor = Color(lightSwatch)
                    val gradient = arrayOf(
                        0.0f to dominantColor,
                        1f to lightColor
                    )
                    Brush.verticalGradient(colorStops = gradient)
                } else {
                    Brush.linearGradient(listOf(dominantColor,dominantColor))
                }
            }else{
                Brush.linearGradient(listOf(defaultBackground,defaultBackground))
            }
        }
    }
}