package com.sri.pokedex.fearure.home

import android.content.res.Configuration
import android.graphics.drawable.BitmapDrawable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.kmpalette.palette.graphics.Palette
import com.sri.pokedex.compose.designsystem.R
import com.sri.pokedex.core.designsystem.component.PokedexAppbar
import com.sri.pokedex.core.designsystem.component.PokedexText
import com.sri.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun SharedTransitionScope.Home(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Column {
        PokedexAppbar()
        HomeContent(
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}

@Composable
fun SharedTransitionScope.HomeContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(50){
                HomeCard(animatedVisibilityScope = animatedVisibilityScope)
            }
        }
    }
}

@Composable
fun SharedTransitionScope.HomeCard(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundColor by palette.paletteBackgroundColor()
    val drawable = ContextCompat.getDrawable(context, R.drawable.pokemon_preview)
    val bmp = (drawable as BitmapDrawable).bitmap.asImageBitmap()
    palette = Palette.from(bmp).generate()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        onClick = { /*TODO*/ },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            containerColor = backgroundColor,
            contentColor = backgroundColor,
            disabledContentColor = backgroundColor,
            disabledContainerColor = backgroundColor
        )
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .size(120.dp),
            painter = painterResource(id = R.drawable.pokemon_preview),
            contentDescription = ""
//            imageModel = { previewImage }
        )

        PokedexText(
            text = "Bulbasaur",
            previewText = "Bulbasaur",
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
    PokedexTheme{
        SharedTransitionScope{
            AnimatedVisibility(visible = true) {
                Home(
                    animatedVisibilityScope = this
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexHomeDarkPreview() {
    PokedexTheme{
        SharedTransitionScope{
            AnimatedVisibility(visible = true) {
                Home(
                    animatedVisibilityScope = this
                )
            }
        }
    }
}

@Preview
@Composable
private fun PokedexHomeContentLightPreview() {
    PokedexTheme{
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeContent(
                    animatedVisibilityScope = this
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexHomeContentDarkPreview() {
    PokedexTheme{
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeContent(
                    animatedVisibilityScope = this
                )
            }
        }
    }
}

@Preview
@Composable
private fun PokedexCardLightPreview() {
    PokedexTheme{
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeCard(animatedVisibilityScope = this)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexCardDarkPreview() {
    PokedexTheme{
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                HomeCard(animatedVisibilityScope = this)
            }
        }
    }
}
