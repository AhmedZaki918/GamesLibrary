package com.example.gameslibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gameslibrary.R
import com.example.gameslibrary.data.model.games.GamesResults
import com.example.gameslibrary.data.model.genres.Results
import com.example.gameslibrary.presentation.navigation.Screen
import com.example.gameslibrary.ui.theme.LightBlack
import com.example.gameslibrary.ui.theme.MEDIUM_MARGIN
import com.example.gameslibrary.util.ErrorUi
import com.example.gameslibrary.util.LoadingIndicator
import com.example.gameslibrary.util.RequestState


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsState().value
    val games = viewModel.gamesFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack)
            .padding(bottom = 70.dp)
    ) {
        handleGenreState(uiState, viewModel)

        val refreshState = games.loadState.refresh
        when (refreshState) {
            is LoadState.Loading -> item { LoadingIndicator() }
            is LoadState.Error -> {
                item {
                    ErrorUi(
                        onRetry = {
                            viewModel.onIntent(HomeIntent.RetryRequest)
                        }
                    )
                }
            }

            is LoadState.NotLoading -> {
                gamesByGenre(
                    games = games,
                    onItemClicked = { slug ->
                        navController.navigate(
                            "${Screen.DETAILS_SCREEN.route}/${slug}"
                        )
                    }
                )
            }
        }
    }
}


fun LazyListScope.handleGenreState(
    uiState: HomeUiState,
    viewModel: HomeViewModel
) {
    when (uiState.genreState) {
        RequestState.SUCCESS -> {
            genres(
                uiState.genresResponse.results,
                onItemClicked = { id ->
                    viewModel.onIntent(
                        HomeIntent.OnGenreClicked(id)
                    )
                })
        }

        RequestState.LOADING -> item { LoadingIndicator() }
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


fun LazyListScope.genres(
    genres: List<Results>,
    onItemClicked: (Int?) -> Unit
) {
    item {
        LazyRow(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = MEDIUM_MARGIN),
            horizontalArrangement = Arrangement.spacedBy(MEDIUM_MARGIN)
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
    games: LazyPagingItems<GamesResults>,
    onItemClicked: (String) -> Unit
) {
    items(games.itemCount) { index ->
        games[index]?.let { game ->
            ListItemGame(
                currentItem = game,
                onItemClicked = { slug ->
                    onItemClicked(slug)
                }
            )
        }
    }

    games.apply {
        if (loadState.append is LoadState.Loading) {
            item { LoadingIndicator() }
        } else if (loadState.append is LoadState.Error) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = MEDIUM_MARGIN),
                        color = Color.Gray,
                        text = stringResource(R.string.error_loading_more)
                    )
                }
            }
        }
    }
}