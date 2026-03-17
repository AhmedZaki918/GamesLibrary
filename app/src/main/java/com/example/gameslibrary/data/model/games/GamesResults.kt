package com.example.gameslibrary.data.model.games

import com.squareup.moshi.Json

data class GamesResults(
    val added: Int? = 0,
    val background_image: String? = "",
    val clip: Any? = Any(),
    @param:Json(name = "dominant_color") val dominantColor: String? = "",
    @param:Json(name = "esrb_rating") val esrbRating: EsrbRating? = EsrbRating(),
    val genres: List<Genre>? = listOf(),
    val id: Int? = 0,
    val metacritic: Int? = 0,
    val name: String? = "",
    val platforms: List<Platforms>? = listOf(),
    val playtime: Int? = 0,
    val rating: Double? = 0.0,
    @param:Json(name = "rating_top") val ratingTop: Int? = 0,
    val ratings: List<Rating>? = listOf(),
    @param:Json(name = "ratings_count") val ratingsCount: Int? = 0,
    val released: String? = "",
    @param:Json(name = "reviews_count") val reviewsCount: Int? = 0,
    @param:Json(name = "reviews_text_count") val reviewsTextCount: Int? = 0,
    @param:Json(name = "saturated_color") val saturatedColor: String? = "",
    val score: Any? = Any(),
    val slug: String? = "",
    val stores: List<Store>? = listOf(),
    @param:Json(name = "suggestions_count") val suggestionsCount: Int? = 0,
    val tags: List<Tag>? = listOf(),
    val tba: Boolean? = false,
    val updated: String? = "",
    @param:Json(name = "user_game") val userGame: Any? = Any()
)
