package com.example.gameslibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.gameslibrary.presentation.navigation.NavGraph
import com.example.gameslibrary.ui.theme.GamesLibraryTheme
import com.example.gameslibrary.util.SetBlackStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamesLibraryTheme {
                SetBlackStatusBar()
                MainUi()
            }
        }
    }
}

@Composable
fun MainUi() {
    val navController = rememberNavController()
    NavGraph(
        navController = navController
    )
}
