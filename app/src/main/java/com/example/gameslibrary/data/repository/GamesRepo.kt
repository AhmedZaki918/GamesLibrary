package com.example.gameslibrary.data.repository

import com.example.gameslibrary.data.network.APIService
import com.example.gameslibrary.data.network.SafeApiCall
import jakarta.inject.Inject

class GamesRepo @Inject constructor(
    private val api: APIService
) : SafeApiCall {

    suspend fun getAllGenres() = safeApiCall {
        api.genres()
    }

    suspend fun getAllGames(genreId : String) = safeApiCall {
        api.gamesByGenre(genre = genreId)
    }
}