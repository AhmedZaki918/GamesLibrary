package com.example.gameslibrary.data.model.games

data class GamesResults(
    val added: Int? = 0,
    val background_image: String? = "",
    val id: Int? = 0,
    val metacritic: Int? = 0,
    val name: String? = "",
    val platforms: List<Platforms>? = listOf(),
    val playtime: Int? = 0,
    val rating: Double? = 0.0,
    val released: String? = "",
    val slug: String? = "",
    val stores: List<Store>? = listOf(),
    val tags: List<Tag>? = listOf()
)
