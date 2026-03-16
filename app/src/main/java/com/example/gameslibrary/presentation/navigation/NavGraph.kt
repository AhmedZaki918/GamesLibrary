package com.example.gameslibrary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gameslibrary.presentation.details.DetailsScreen
import com.example.gameslibrary.presentation.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.HOME_SCREEN.route
    ) {
        composable(route = Screen.HOME_SCREEN.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.DETAILS_SCREEN.route) {
            DetailsScreen(navController)
        }
    }
}