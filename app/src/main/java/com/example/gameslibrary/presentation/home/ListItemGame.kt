package com.example.gameslibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.gameslibrary.R
import com.example.gameslibrary.data.model.games.GamesResults
import com.example.gameslibrary.ui.theme.LARGE_MARGIN
import com.example.gameslibrary.ui.theme.LightBlack
import com.example.gameslibrary.ui.theme.MEDIUM_MARGIN
import com.example.gameslibrary.ui.theme.SMALL_MARGIN


@Composable
fun ListItemGame(
    currentItem: GamesResults,
    onItemClicked: (String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .background(LightBlack)
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable {
                onItemClicked(currentItem.slug.toString())
            }
    ) {

        val (posterImage, titleText, rateText, releaseDateText) = createRefs()

        AsyncImage(
            model = currentItem.background_image,
            contentDescription = stringResource(R.string.game_poster_accessibility),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(posterImage) {
                    top.linkTo(parent.top, LARGE_MARGIN)
                    start.linkTo(parent.start, MEDIUM_MARGIN)
                }
                .size(55.dp)
                .clip(RoundedCornerShape(SMALL_MARGIN))
        )


        Text(
            modifier = Modifier
                .constrainAs(titleText) {
                    top.linkTo(posterImage.top)
                    start.linkTo(posterImage.end, MEDIUM_MARGIN)
                }
                .fillMaxWidth(0.7f),
            maxLines = 1,
            text = currentItem.name.toString(),
            fontSize = 14.sp,
            color = Color.White
        )


        Text(
            modifier = Modifier.constrainAs(rateText) {
                top.linkTo(titleText.bottom)
                start.linkTo(posterImage.end, MEDIUM_MARGIN)
            },
            text = "${stringResource(R.string.rate)} ${currentItem.rating}",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.8f)
        )


        Text(
            modifier = Modifier.constrainAs(releaseDateText) {
                top.linkTo(rateText.bottom)
                start.linkTo(posterImage.end, MEDIUM_MARGIN)
            },
            fontSize = 12.sp,
            lineHeight = 16.sp,
            text = "${stringResource(R.string.released)} ${currentItem.released}",
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}