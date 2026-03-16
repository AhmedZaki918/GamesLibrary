package com.example.gameslibrary.util

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.gameslibrary.R
import com.example.gameslibrary.ui.theme.Purple80

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
                .fillMaxSize()
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
fun ErrorUi() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = Black,
            text = stringResource(R.string.something_went_wrong),
        )
    }
}
