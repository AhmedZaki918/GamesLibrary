package com.example.gameslibrary.data.model.genres

import com.example.gameslibrary.data.model.genres.Game
import com.squareup.moshi.Json

data class GenresDto(
    val games: List<Game>? = null,
    @param:Json(name = "games_count") val gamesCount: Int? = null,
    val id: Int? = null,
    @param:Json(name = "image_background") val imageBackground: String? = null,
    val name: String? = null,
    val slug: String? = null,
    val isClicked: Boolean = false
)