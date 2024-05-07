package com.sri.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.sri.pokedex.core.navigation.AppComposeNavigator
import com.sri.pokedex.core.navigation.LocalComposeNavigator
import com.sri.pokedex.ui.PokedexMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var composeNavigator: AppComposeNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalComposeNavigator provides composeNavigator
            ) {
                PokedexMain(
                    composeNavigator = composeNavigator
                )
            }
        }
    }
}