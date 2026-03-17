package com.example.gameslibrary.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gameslibrary.data.local.Constants.GAME_ID
import com.example.gameslibrary.data.network.Resource
import com.example.gameslibrary.data.repository.GamesRepo
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
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: GamesRepo
) : BaseViewModel<DetailsIntent>() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>(GAME_ID)?.let {
            displayGameDetails(it)
            saveSlug(it)
        }
    }

    override fun onIntent(intent: DetailsIntent) {
        if (intent is DetailsIntent.RetryRequest) {
            displayGameDetails(uiState.value.slug)
        }
    }

    private fun displayGameDetails(slug: String) {
        viewModelScope.launch {
            initLoading()
            val response = repo.getGameDetails(slug)

            if (response is Resource.Success) {
                _uiState.update {
                    it.copy(
                        game = response.data,
                        detailsState = RequestState.SUCCESS
                    )
                }

            } else {
                _uiState.update {
                    it.copy(
                        detailsState = RequestState.ERROR
                    )
                }
            }
        }
    }

    private fun saveSlug(slug: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    slug = slug
                )
            }
        }
    }

    private fun initLoading() {
        _uiState.update {
            it.copy(
                detailsState = RequestState.LOADING
            )
        }
    }
}