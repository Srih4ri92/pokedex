package com.sri.pokedex.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sri.pokedex.core.navigation.PokedexScreens
import com.sri.pokedex.fearure.home.PokedexHome
import com.sri.pokedex.feature.detail.PokedexDetails

context(SharedTransitionScope)
fun NavGraphBuilder.pokemonNavigation(){

    composable(
        route = PokedexScreens.Home.name
    ){
        PokedexHome(
            this
        )
    }

    composable(
        route = PokedexScreens.Detail.name,
        arguments = PokedexScreens.Detail.navArguments
    ){
        PokedexDetails(this)
    }
}