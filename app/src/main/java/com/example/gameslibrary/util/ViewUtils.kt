package com.example.gameslibrary.util

import android.app.Activity
import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.gameslibrary.R
import com.example.gameslibrary.ui.theme.MEDIUM_MARGIN
import com.example.gameslibrary.ui.theme.Purple80
import com.example.gameslibrary.ui.theme.VERY_SMALL_MARGIN

@Composable
fun SetBlackStatusBar() {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window ?: return

    SideEffect {
        val controller = WindowCompat.getInsetsController(window, view)
        // STATUS BAR → white icons
        controller.isAppearanceLightStatusBars = false
        // NAVIGATION BAR → white icons
        controller.isAppearanceLightNavigationBars = false
        // NAVIGATION BAR → black background
        window.navigationBarColor = Color.Black.toArgb()
    }
}


@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true
) {
    if (isVisible) {
        Box(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            LinearProgressIndicator(
                color = Purple80,
                modifier = Modifier.fillMaxWidth(0.25f)
            )
        }
    }
}


@Composable
fun ErrorUi(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 250.dp)
            .clickable {
                onRetry()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_icon),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )


        Text(
            modifier = Modifier.padding(top = MEDIUM_MARGIN),
            color = Color.Gray,
            text = stringResource(R.string.something_went_wrong),
        )

        Text(
            modifier = Modifier.padding(top = VERY_SMALL_MARGIN),
            text = "Try again",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}

fun formatText(text: String): String {
    return Html.fromHtml(
        text,
        Html.FROM_HTML_MODE_LEGACY
    ).toString()
}
