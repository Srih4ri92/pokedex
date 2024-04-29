package com.sri.pokedex.core.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val pokedexAppDispatchers: PokedexAppDispatchers)
enum class PokedexAppDispatchers {
    IO
}