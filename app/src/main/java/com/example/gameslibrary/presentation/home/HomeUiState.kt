package com.example.gameslibrary.presentation.home

import com.example.gameslibrary.data.model.GenresDto
import com.example.gameslibrary.util.RequestState

data class HomeUiState(
    val homeState: RequestState = RequestState.IDLE,
    val genresResponse: List<GenresDto> = emptyList()
)
