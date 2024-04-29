package com.sri.pokedex.core.network.model

import androidx.core.app.NotificationCompat.MessagingStyle.Message

data class PokemonErrorResponse(
    val cide: Int,
    val message: String?
)
