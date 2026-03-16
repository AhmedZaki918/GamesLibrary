package com.example.gameslibrary.presentation.home

sealed class HomeIntent {
    data class OnGenreClicked(
        val id: Int?
    ) : HomeIntent()
}