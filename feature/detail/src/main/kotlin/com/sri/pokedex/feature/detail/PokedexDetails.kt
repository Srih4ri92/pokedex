package com.sri.pokedex.feature.detail

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.sri.pokedex.compose.designsystem.R
import com.sri.pokedex.core.data.repository.detail.FakeDetailRepository
import com.sri.pokedex.core.designsystem.component.PokedexCircularProgress
import com.sri.pokedex.core.designsystem.component.PokedexText
import com.sri.pokedex.core.designsystem.component.pokedexSharedElement
import com.sri.pokedex.core.designsystem.theme.PokedexTheme
import com.sri.pokedex.core.designsystem.utils.getPokemonTypeColor
import com.sri.pokedex.core.model.Pokemon
import com.sri.pokedex.core.model.PokemonInfo
import com.sri.pokedex.core.navigation.boundsTransform
import com.sri.pokedex.core.navigation.currentComposeNavigator
import com.sri.pokedex.core.preview.PokedexPreviewTheme
import com.sri.pokedex.core.preview.PreviewUtils

@Composable
fun SharedTransitionScope.PokedexDetails(
    animatedVisibilityScope: AnimatedVisibilityScope,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
    val pokemonInfo by detailsViewModel.pokemonInfo.collectAsStateWithLifecycle()
    val pokemon by detailsViewModel.pokemon.collectAsStateWithLifecycle()
    val id by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        PokedexDetailsHeader(
            animatedVisibilityScope = animatedVisibilityScope,
            pokemon = pokemon,
            pokemonInfo = pokemonInfo
        )

        if (uiState == DetailsUiState.Idle && pokemonInfo != null) {
            PokedexDetailsInfo(pokemonInfo = pokemonInfo!!)
            PokedexDetailsStats(pokemonInfo = pokemonInfo!!)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                PokedexCircularProgress()
            }
        }
    }
}


@Composable
private fun SharedTransitionScope.PokedexDetailsHeader(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon?,
    pokemonInfo: PokemonInfo?
) {
    val isLocalInspectionMode = LocalInspectionMode.current
    val currentNavigator = currentComposeNavigator
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
                    .padding(end = 6.dp)
                    .clickable { currentNavigator.navigateUp() },
                painter = painterResource(id = R.drawable.ic_arrow),
                tint = PokedexTheme.colors.absoluteWhite,
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = if (isLocalInspectionMode) "Bulbasaur" else pokemon?.name!!,
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
            text = pokemonInfo?.getIdString().orEmpty()
        )
        GlideImage(
            imageModel = { pokemon?.imageUrl },
            previewPlaceholder = painterResource(id = R.drawable.pokemon_preview),
            modifier = Modifier
                .align(Alignment.Center)
                .size(192.dp)
                .pokedexSharedElement(
                    isLocalInspectionMode = isLocalInspectionMode,
                    state = rememberSharedContentState(key = "image-${pokemon?.imageUrl}"),
                    boundsTransform = boundsTransform,
                    animatedVisibilityScope = animatedVisibilityScope
                ),
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
    PokedexText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .pokedexSharedElement(
                isLocalInspectionMode = isLocalInspectionMode,
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = boundsTransform,
                state = rememberSharedContentState(key = "name-${pokemon?.name}")
            ),
        previewText = "Bulbasaur",
        text = pokemon?.name.orEmpty(),
        color = PokedexTheme.colors.black,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
    )

}

@Composable
private fun PokedexDetailsInfo(
    pokemonInfo: PokemonInfo
) {

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally)
        ) {
            pokemonInfo.types.forEach { pokemon ->
                PokedexText(
                    modifier = Modifier
                        .background(
                            color = getPokemonTypeColor(type = pokemon.type.name),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 40.dp),
                    text = pokemon.type.name,
                    color = PokedexTheme.colors.absoluteWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            PokedexInfoItem(
                value = pokemonInfo.getHeightString(),
                title = stringResource(id = R.string.height),
            )

            PokedexInfoItem(
                value = pokemonInfo.getWeightString(),
                title = stringResource(id = R.string.weight)
            )

        }
    }
}

@Composable
private fun PokedexDetailsStats(
    pokemonInfo: PokemonInfo
) {
    Column {
        pokemonInfo.toPokedexStatusList().forEach { pokemonStatus ->
            PokedexStatItem(
                modifier = Modifier.padding(bottom = 16.dp),
                status = pokemonStatus
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexDetailScreenPreview() {
    PokedexPreviewTheme {
        PokedexDetails(
            animatedVisibilityScope = it,
            detailsViewModel = DetailsViewModel(
                detailRepository = FakeDetailRepository(),
                savedStateHandle = SavedStateHandle()
            )
        )

    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexDetailsInfoPreview() {
    PokedexPreviewTheme {
        AnimatedVisibility(visible = true) {
            PokedexDetailsInfo(
                pokemonInfo = PreviewUtils.mockPokemonInfo()
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexDetailsStatsPreview() {
    PokedexPreviewTheme {
        PokedexDetailsStats(
            pokemonInfo = PreviewUtils.mockPokemonInfo()
        )
    }
}