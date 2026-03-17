package com.example.gameslibrary.presentation.details

import com.example.gameslibrary.data.model.details.DetailsDto
import com.example.gameslibrary.util.RequestState

data class DetailsUiState(
    val detailsState: RequestState = RequestState.IDLE,
    val game : DetailsDto = DetailsDto(),
    val slug : String = ""
)
