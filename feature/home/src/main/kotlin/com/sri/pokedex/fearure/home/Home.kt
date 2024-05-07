package com.sri.pokedex.fearure.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.sri.pokedex.compose.designsystem.R
import com.sri.pokedex.core.data.repository.home.FakeHomeReopsitory
import com.sri.pokedex.core.designsystem.component.PokedexAppbar
import com.sri.pokedex.core.designsystem.component.PokedexCircularProgress
import com.sri.pokedex.core.designsystem.component.PokedexText
import com.sri.pokedex.core.designsystem.theme.PokedexTheme
import com.sri.pokedex.core.model.Pokemon
import com.sri.pokedex.core.navigation.PokedexScreens
import com.sri.pokedex.core.navigation.currentComposeNavigator
import com.sri.pokedex.core.preview.PreviewUtils

@Composable
fun SharedTransitionScope.Home(
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val pokemonList = viewModel.pokemonList.collectAsStateWithLifecycle()
    PokedexTheme {
        Column {
            PokedexAppbar()
            HomeContent(
                animatedVisibilityScope = animatedVisibilityScope,
                pokemonList = pokemonList.value,
                uiState = uiState.value,
                fetchNextPokemonList = viewModel::fetchNextPokemonList
            )
        }
    }
}

@Composable
private fun SharedTransitionScope.HomeContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonList: List<Pokemon>,
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    fetchNextPokemonList: () -> Unit,
) {
    val threadHold = 8
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            itemsIndexed(pokemonList) { index, pokemon ->
                if ((index + threadHold) >= pokemonList.size && uiState != HomeUiState.Loading) {
                    fetchNextPokemonList()
                }
                HomeCard(
                    animatedVisibilityScope = animatedVisibilityScope,
                    pokemon = pokemon
                )
            }
        }

        if (uiState == HomeUiState.Loading) {
            PokedexCircularProgress()
        }
    }
}

@Composable
private fun SharedTransitionScope.HomeCard(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    val currentNavigator = currentComposeNavigator
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundColor by palette.paletteBackgroundColor()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        onClick = { currentNavigator.navigate(PokedexScreens.Detail.createRoute(pokemon)) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            containerColor = backgroundColor,
            contentColor = backgroundColor,
            disabledContentColor = backgroundColor,
            disabledContainerColor = backgroundColor
        )
    ) {
        GlideImage(
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(120.dp),
            imageModel = { pokemon.imageUrl },
            previewPlaceholder = painterResource(id = R.drawable.pokemon_preview),
            imageOptions = ImageOptions(contentScale = ContentScale.Inside),
            component = rememberImageComponent {
                +CrossfadePlugin()
                +ShimmerPlugin(
                    Shimmer.Resonate(
                        baseColor = Color.Transparent,
                        highlightColor = Color.LightGray,
                    ),
                )

                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = pokemon.imageUrl,
                        useCache = true,
                        paletteLoadedListener = { palette = it },
                    )
                }
            }

        )

        PokedexText(
            text = pokemon.name,
            previewText = pokemon.name,
            fontSize = 16.sp,
            color = PokedexTheme.colors.black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 4.dp
                )
        )
    }
}

@Preview
@Composable
private fun PokedexHomeLightPreview() {
    PokedexTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                Home(
                    animatedVisibilityScope = this,
                    viewModel = HomeViewModel(homeRepository = FakeHomeReopsitory())
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexHomeDarkPreview() {
    PokedexTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                Home(
                    animatedVisibilityScope = this,
                    viewModel = HomeViewModel(homeRepository = FakeHomeReopsitory())
                )
            }
        }
    }
}

@Preview
@Composable
private fun PokedexHomeContentLightPreview() {
    PokedexTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeContent(
                    animatedVisibilityScope = this,
                    pokemonList = PreviewUtils.mockPokemonList(),
                    uiState = HomeUiState.Idle,
                    fetchNextPokemonList = { HomeViewModel(homeRepository = FakeHomeReopsitory()) }
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexHomeContentDarkPreview() {
    PokedexTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeContent(
                    animatedVisibilityScope = this,
                    pokemonList = PreviewUtils.mockPokemonList(),
                    uiState = HomeUiState.Idle,
                    fetchNextPokemonList = { HomeViewModel(homeRepository = FakeHomeReopsitory()) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PokedexCardLightPreview() {
    PokedexTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeCard(
                    animatedVisibilityScope = this, pokemon = PreviewUtils.mockPokemon()
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexCardDarkPreview() {
    PokedexTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeCard(
                    animatedVisibilityScope = this, pokemon = PreviewUtils.mockPokemon()
                )
            }
        }
    }
}
