package com.example.gameslibrary.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameslibrary.data.model.genres.GenresDto
import com.example.gameslibrary.ui.theme.Purple80

@Composable
fun ListItemCategory(
    currentItem: GenresDto,
    onCategoryClicked: (Int?) -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .border(
                width = 0.5.dp,
                color = if (currentItem.isClicked) Purple80
                else Gray.copy(alpha = 0.7f),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onCategoryClicked(currentItem.id)
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            text = currentItem.name.toString(),
            color = if (currentItem.isClicked) Purple80
            else androidx.compose.ui.graphics.Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
    }
}