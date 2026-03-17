package com.example.gameslibrary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gameslibrary.data.model.games.GamesResults
import com.example.gameslibrary.data.network.APIService
import com.example.gameslibrary.data.network.SafeApiCall
import com.example.gameslibrary.data.pagination.GamesPagingSource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GamesRepo @Inject constructor(
    private val api: APIService
) : SafeApiCall {

    suspend fun getAllGenres() = safeApiCall {
        api.genres()
    }

    fun getGames(genreId: String): Flow<PagingData<GamesResults>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GamesPagingSource(api, genreId) }
        ).flow
    }

    suspend fun getGameDetails(gameId: String) = safeApiCall {
        api.gameDetails(gameId = gameId)
    }
}