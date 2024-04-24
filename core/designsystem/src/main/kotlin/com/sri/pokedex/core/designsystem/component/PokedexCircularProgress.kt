package com.sri.pokedex.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import com.sri.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun BoxScope.PokedexCircularProgress() {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = PokedexTheme.colors.primary
    )
}

@Preview
@Composable
private fun PokedexCircularProgressPreview() {
    PokedexTheme{
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            PokedexCircularProgress()
        }
    }

}