package com.example.gameslibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gameslibrary.data.model.games.GamesDto
import com.example.gameslibrary.data.model.genres.GenresDto
import com.example.gameslibrary.ui.theme.LightBlack
import com.example.gameslibrary.util.ErrorUi
import com.example.gameslibrary.util.LoadingIndicator
import com.example.gameslibrary.util.RequestState


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack)
            .padding(bottom = 70.dp)
    ) {

        when (uiState.genreState) {
            RequestState.SUCCESS -> {
                genres(
                    uiState.genresResponse,
                    onItemClicked = { id ->
                        viewModel.onIntent(
                            HomeIntent.OnGenreClicked(id)
                        )
                    })

                gamesByGenre(
                    uiState.gamesResponse,
                    onItemClicked = {}
                )
            }

            RequestState.LOADING -> {
                item { LoadingIndicator() }
            }

            RequestState.ERROR -> {
                item {
                    ErrorUi(
                        onRetry = {
                            viewModel.onIntent(HomeIntent.RetryRequest)
                        }
                    )
                }
            }

            else -> Unit
        }
    }
}


fun LazyListScope.genres(
    genres: List<GenresDto>,
    onItemClicked: (Int?) -> Unit
) {
    item {
        LazyRow(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(genres.take(5)) { item ->
                ListItemCategory(
                    currentItem = item,
                    onCategoryClicked = { id ->
                        onItemClicked(id)
                    }
                )
            }
        }
    }
}


fun LazyListScope.gamesByGenre(
    games: List<GamesDto>,
    onItemClicked: () -> Unit
) {
    items(games) { game ->
        ListItemGame(
            game,
            onItemClicked = {
                onItemClicked()
            }
        )
    }
}