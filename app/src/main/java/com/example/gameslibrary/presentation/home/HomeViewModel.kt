package com.example.gameslibrary.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.gameslibrary.data.network.Resource
import com.example.gameslibrary.data.repository.GenresRepo
import com.example.gameslibrary.util.BaseViewModel
import com.example.gameslibrary.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genresRepo: GenresRepo
) : BaseViewModel<HomeIntent>() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    init {
        displayGenres()
    }

    override fun onIntent(intent: HomeIntent) {
        if (intent is HomeIntent.OnGenreClicked) {
            setGenreActive(intent.id)
        }
    }


    private fun displayGenres() {
        viewModelScope.launch {
            initLoading()
            val response = genresRepo.getAllGenres()

            if (response is Resource.Success) {
                _uiState.update {
                    it.copy(
                        genresResponse = response.data,
                        homeState = RequestState.SUCCESS
                    )
                }

            } else {
                _uiState.update {
                    it.copy(
                        homeState = RequestState.ERROR
                    )
                }
            }
        }
    }


    private fun setGenreActive(genreId: Int?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    genresResponse = it.genresResponse.map { genre ->
                        if (genre.id == genreId) {
                            genre.copy(isClicked = true)
                        } else {
                            genre.copy(isClicked = false)
                        }
                    })
            }
        }
    }

    private fun initLoading() {
        _uiState.update {
            it.copy(
                homeState = RequestState.LOADING
            )
        }
    }
}