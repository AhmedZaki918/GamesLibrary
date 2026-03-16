package com.example.gameslibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gameslibrary.data.model.GenresDto
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
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack)
            .padding(bottom = 70.dp)
    ) {

        when (uiState.homeState) {
            RequestState.SUCCESS -> {
                homeCategories(
                    uiState.genresResponse,
                    onItemClicked = { id ->
                        viewModel.onIntent(
                            HomeIntent.OnGenreClicked(id)
                        )
                    })
            }

            RequestState.LOADING -> {
                item { LoadingIndicator() }
            }

            RequestState.ERROR -> {
                item { ErrorUi() }
            }

            else -> Unit
        }
    }
}


fun LazyListScope.homeCategories(
    genres: List<GenresDto>,
    onItemClicked: (Int?) -> Unit
) {
    item {
        LazyRow(
            modifier = Modifier.padding(top = 50.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(genres) { item ->
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