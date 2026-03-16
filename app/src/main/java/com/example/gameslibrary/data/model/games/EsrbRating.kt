package com.example.gameslibrary.data.model.games

import com.squareup.moshi.Json

data class EsrbRating(
    val id: Int? = null,
    val name: String? = null,
    @param:Json(name = "name_en") val nameEn: String? = null,
    val slug: String? = null
)