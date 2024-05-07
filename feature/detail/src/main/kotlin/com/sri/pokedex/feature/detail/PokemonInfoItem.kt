package com.sri.pokedex.feature.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sri.pokedex.core.designsystem.component.PokedexText
import com.sri.pokedex.core.designsystem.theme.PokedexTheme
import com.sri.pokedex.core.preview.PokedexPreviewTheme

@Composable
fun PokedexInfoItem(
    value: String,
    title: String,
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokedexText(
            text = value,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = PokedexTheme.colors.black,
            fontSize = 24.sp
        )
        PokedexText(
            text = title,
            textAlign = TextAlign.Center,
            color = PokedexTheme.colors.white56,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokemonInfoItemPreview() {
    PokedexPreviewTheme {
        PokedexInfoItem(
            value = "10 kg", title = "Weight"
        )
    }
}