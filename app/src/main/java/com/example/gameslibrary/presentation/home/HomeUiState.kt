package com.example.gameslibrary.presentation.home

import com.example.gameslibrary.data.model.games.GamesDto
import com.example.gameslibrary.data.model.genres.GenresDto
import com.example.gameslibrary.util.RequestState

data class HomeUiState(
    val genreState: RequestState = RequestState.IDLE,
    val genresResponse: GenresDto = GenresDto(),
    val gamesResponse: GamesDto = GamesDto(),
    val genreIdAtSelectedIndex : Int? = 0
)
