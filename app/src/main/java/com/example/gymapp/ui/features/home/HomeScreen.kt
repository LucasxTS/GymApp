package com.example.gymapp.ui.features.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gymapp.ui.components.HomeHeader

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = HomeViewModel()
    val treinos by viewModel.treinos.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        HomeHeader()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {

}