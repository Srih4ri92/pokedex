package com.sri.pokedex.feature.detail

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.sri.pokedex.core.designsystem.theme.PokedexTheme
import com.sri.pokedex.core.preview.PokedexPreviewTheme
import com.sri.pokedex.compose.designsystem.R
import com.sri.pokedex.core.designsystem.component.PokedexText
import com.sri.pokedex.core.model.Pokemon
import com.sri.pokedex.core.model.PokemonInfo

@Composable
fun SharedTransitionScope.Details(
    animatedVisibilityScope: AnimatedVisibilityScope,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
    val pokemonInfo by detailsViewModel.pokemonInfo.collectAsStateWithLifecycle()
    val pokemon by detailsViewModel.pokemon.collectAsStateWithLifecycle()

    PokedexTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ){
            DetailsHeader(
                animatedVisibilityScope = animatedVisibilityScope,
                pokemon = pokemon,
                pokemonInfo = pokemonInfo
            )
        }
    }
}



@Composable
fun DetailsHeader(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon?,
    pokemonInfo: PokemonInfo?
) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backGroundBrush by palette.paletteBackgroundBrush()
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = 64.dp,
        bottomStart = 64.dp
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .shadow(elevation = 9.dp, shape = shape)
            .background(
                shape = shape,
                brush = backGroundBrush
            )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 6.dp),
                painter = painterResource(id = R.drawable.ic_arrow),
                tint = PokedexTheme.colors.absoluteWhite,
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = pokemonInfo?.name!!,
                color = PokedexTheme.colors.absoluteWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
        PokedexText(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .statusBarsPadding(),
            previewText = "#001",
            color = PokedexTheme.colors.absoluteWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            text = ""
        )
        GlideImage(
            imageModel = { pokemon?.imageUrl},
            previewPlaceholder = painterResource(id = R.drawable.pokemon_preview),
            modifier = Modifier
                .align(Alignment.Center)
                .size(192.dp),
            component = rememberImageComponent {
                +CrossfadePlugin()

                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = pokemon?.imageUrl,
                        useCache = true,
                        paletteLoadedListener = { palette = it },
                    )
                }
            }
        )
    }


}

@Preview
@Composable
private fun DetailScreenPreviewLight() {
    PokedexPreviewTheme{
        AnimatedVisibility(visible = true) {
            Details(
                animatedVisibilityScope = this
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailScreenPreviewDark() {
    PokedexPreviewTheme{
        AnimatedVisibility(visible = true) {
            Details(
                animatedVisibilityScope = this
            )
        }
    }
}
