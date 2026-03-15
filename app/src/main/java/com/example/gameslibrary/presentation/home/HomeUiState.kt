package com.example.gameslibrary.presentation.home

import com.example.gameslibrary.util.RequestState

data class HomeUiState(
    val homeState: RequestState = RequestState.IDLE
)
