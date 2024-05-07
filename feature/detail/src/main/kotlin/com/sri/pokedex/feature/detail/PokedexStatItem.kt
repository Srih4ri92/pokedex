package com.sri.pokedex.feature.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sri.pokedex.core.designsystem.component.PokedexProgressBar
import com.sri.pokedex.core.designsystem.component.PokedexText
import com.sri.pokedex.core.designsystem.theme.PokedexTheme
import com.sri.pokedex.core.preview.PokedexPreviewTheme
import com.sri.pokedex.core.preview.PreviewUtils

@Composable
internal fun PokedexStatItem(
    modifier: Modifier = Modifier,
    status: PokedexStatus
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PokedexText(
            text = status.type,
            modifier = Modifier
                .padding(start = 32.dp)
                .widthIn(min = 20.dp),
            color = PokedexTheme.colors.white70,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
        PokedexProgressBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            progress = status.progress,
            color = status.color,
            label = status.label
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexStatItemPreview() {
    PokedexPreviewTheme {
        PokedexStatItem(
            status = PreviewUtils.mockPokemonInfo().toPokedexStatusList().get(0)
        )
    }
}