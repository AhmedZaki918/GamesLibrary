package com.example.gameslibrary.data.model.games

import com.squareup.moshi.Json

data class Tag(
    @param:Json(name = "games_count") val gamesCount: Int? = null,
    val id: Int? = null,
    @param:Json(name = "image_background") val imageBackground: String? = null,
    val language: String? = null,
    val name: String? = null,
    val slug: String? = null
)