package com.example.gameslibrary.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gameslibrary.data.model.games.GamesResults
import com.example.gameslibrary.data.network.Resource
import com.example.gameslibrary.data.repository.GamesRepo
import com.example.gameslibrary.util.BaseViewModel
import com.example.gameslibrary.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: GamesRepo
) : BaseViewModel<HomeIntent>() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var _gamesFlow: Flow<PagingData<GamesResults>> = flowOf(PagingData.empty())
    val gamesFlow: Flow<PagingData<GamesResults>> get() = _gamesFlow

    init {
        displayGenres()
    }

    override fun onIntent(intent: HomeIntent) {
        if (intent is HomeIntent.OnGenreClicked) {
            setGenreSelected(intent.id)
            displayGamesByGenre(intent.id)
        } else if (intent is HomeIntent.RetryRequest) {
            displayGenres()
        }
    }


    private fun displayGenres() {
        viewModelScope.launch {
            initLoading()
            val response = repo.getAllGenres()

            if (response is Resource.Success) {
                _uiState.update {
                    it.copy(
                        genresResponse = response.data,
                        genreState = RequestState.SUCCESS
                    )
                }
                setFirstGenreSelected()
                displayGamesByGenre(uiState.value.genreIdAtSelectedIndex)

            } else {
                _uiState.update {
                    it.copy(
                        genreState = RequestState.ERROR
                    )
                }
            }
        }
    }


    private fun displayGamesByGenre(genreId: Int?) {
        _gamesFlow = repo.getGames(genreId.toString())
            .cachedIn(viewModelScope)
    }


    private fun setFirstGenreSelected() {
        viewModelScope.launch {
            // We need this genre id to get the selected games based on it.
            var genreId: Int? = 0

            _uiState.update {
                it.copy(
                    genresResponse = it.genresResponse.copy(
                        results = it.genresResponse.results.mapIndexed { index, item ->
                            if (index == 0) {
                                genreId = item.id
                                item.copy(isClicked = true)
                            } else {
                                item.copy(isClicked = false)
                            }
                        }
                    ),
                    genreIdAtSelectedIndex = genreId
                )
            }
        }
    }


    private fun setGenreSelected(genreId: Int?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    genresResponse = it.genresResponse.copy(
                        results = it.genresResponse.results.map { genre ->
                            genre.copy(
                                isClicked = genre.id == genreId
                            )
                        }
                    )
                )
            }
        }
    }


    private fun initLoading() {
        // We do "if check" to avoid trigger loading state multiple times
        if (uiState.value.genreState != RequestState.LOADING) {
            _uiState.update {
                it.copy(
                    genreState = RequestState.LOADING
                )
            }
        }
    }
}