package com.example.gameslibrary.presentation.home

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gameslibrary.ui.theme.LightBlack
import com.example.gameslibrary.ui.theme.Purple80

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack).padding(top = 80.dp)
    ) {

        Text(
            color = Purple80,
            text = "Home Screen",
            fontSize = 25.sp,
        )

    }
}