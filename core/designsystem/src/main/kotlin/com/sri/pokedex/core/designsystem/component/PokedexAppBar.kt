package com.sri.pokedex.core.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sri.pokedex.compose.designsystem.R
import com.sri.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun PokedexAppbar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = PokedexTheme.colors.absoluteWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = PokedexTheme.colors.primary
        )
    )
}

@Preview
@Composable
private fun PokedexAppBarPrevirw() {
    PokedexTheme{
        PokedexAppbar()
    }
}