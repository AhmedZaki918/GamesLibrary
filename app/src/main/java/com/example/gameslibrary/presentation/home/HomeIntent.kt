package com.example.gameslibrary.presentation.home

sealed class HomeIntent {
    data class OnCategoryClicked(
        val id: Int
    ) : HomeIntent()
}