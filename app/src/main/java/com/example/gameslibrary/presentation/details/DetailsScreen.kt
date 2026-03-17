package com.example.gameslibrary.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gameslibrary.R
import com.example.gameslibrary.data.model.details.DetailsDto
import com.example.gameslibrary.ui.theme.LARGE_MARGIN
import com.example.gameslibrary.ui.theme.LightBlack
import com.example.gameslibrary.ui.theme.MEDIUM_MARGIN
import com.example.gameslibrary.ui.theme.SMALL_MARGIN
import com.example.gameslibrary.ui.theme.VERY_SMALL_MARGIN
import com.example.gameslibrary.util.ErrorUi
import com.example.gameslibrary.util.LoadingIndicator
import com.example.gameslibrary.util.RequestState
import com.example.gameslibrary.util.formatText

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.details),
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_accessibility)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlack,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlack)
                .verticalScroll(rememberScrollState())
        ) {

            when (uiState.detailsState) {
                RequestState.SUCCESS -> {
                    Game(uiState.game)
                }

                RequestState.LOADING -> {
                    LoadingIndicator()
                }

                RequestState.ERROR -> {
                    ErrorUi(
                        onRetry = {
                            viewModel.onIntent(DetailsIntent.RetryRequest)
                        }
                    )
                }

                else -> Unit
            }
        }
    }
}


@Composable
fun Game(item: DetailsDto) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp, bottom = 70.dp)
    ) {
        val (posterImage, titleText, releasedDateText, rateText,
            overviewText, descriptionText
        ) = createRefs()

        AsyncImage(
            model = item.background_image,
            contentDescription = stringResource(R.string.game_poster_accessibility),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(posterImage) {
                    top.linkTo(parent.top, LARGE_MARGIN)
                    start.linkTo(parent.start, MEDIUM_MARGIN)
                }
                .size(width = 130.dp, height = 190.dp)
                .clip(RoundedCornerShape(8.dp))
        )


        Text(
            modifier = Modifier
                .constrainAs(titleText) {
                    top.linkTo(posterImage.top, SMALL_MARGIN)
                    start.linkTo(posterImage.end, MEDIUM_MARGIN)
                }
                .fillMaxWidth(0.5f),
            text = item.name.toString(),
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.constrainAs(releasedDateText) {
                top.linkTo(titleText.bottom, MEDIUM_MARGIN)
                start.linkTo(posterImage.end, MEDIUM_MARGIN)
            },
            lineHeight = 15.sp,
            fontSize = 12.sp,
            text = "${stringResource(R.string.released)} ${item.released}",
            color = Color.Gray,
        )

        Text(
            modifier = Modifier.constrainAs(rateText) {
                top.linkTo(releasedDateText.bottom, VERY_SMALL_MARGIN)
                start.linkTo(releasedDateText.start)
            },
            lineHeight = 15.sp,
            fontSize = 12.sp,
            text = "${stringResource(R.string.rate)} ${item.rating}",
            color = Color.Gray,
        )

        Text(
            modifier = Modifier
                .constrainAs(overviewText) {
                    top.linkTo(posterImage.bottom, MEDIUM_MARGIN)
                    start.linkTo(posterImage.start)
                },
            fontSize = 14.sp,
            text = stringResource(R.string.overview),
            color = Color.White,
            lineHeight = 20.sp
        )


        Text(
            modifier = Modifier
                .constrainAs(descriptionText) {
                    top.linkTo(overviewText.bottom, SMALL_MARGIN)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = MEDIUM_MARGIN)
                .fillMaxWidth(0.9f),
            fontSize = 12.sp,
            lineHeight = 15.sp,
            text = formatText(item.description.toString()),
            color = Color.White.copy(alpha = 0.8f),
        )
    }
}