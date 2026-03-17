package com.example.gameslibrary.presentation.home

import androidx.paging.PagingData
import com.example.gameslibrary.data.model.games.GamesResults
import com.example.gameslibrary.data.model.genres.GenresDto
import com.example.gameslibrary.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeUiState(
    val genreState: RequestState = RequestState.IDLE,
    val genresResponse: GenresDto = GenresDto(),
    val genreIdAtSelectedIndex: Int? = 0
)
