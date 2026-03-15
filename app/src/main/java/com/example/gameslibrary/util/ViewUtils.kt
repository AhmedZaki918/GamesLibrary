package com.example.gameslibrary.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

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