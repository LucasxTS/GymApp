package com.example.gymapp.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.domain.models.Training
import com.example.gymapp.ui.components.home.FloatingActionButtonView
import com.example.gymapp.ui.components.home.HomeHeader
import com.example.gymapp.ui.components.home.TrainingList
import com.google.firebase.Timestamp

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = HomeViewModel()
    val treinos by viewModel.treinos.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ){
            HomeHeader()
            TrainingList(trainingList = training, viewModel = viewModel)
    }
        FloatingActionButtonView()
    }
}

val training = listOf<Training>(
    Training("Peito", "Segunda e sexta", Timestamp.now()),
    Training("Biceps", "terça", Timestamp.now()),
    Training("Triceps", "quarta", Timestamp.now()),
    Training("Ombro", "quinta", Timestamp.now()),
    Training("Perna", "sabado", Timestamp.now()),
    Training("Posterior de coxa", "nunca", Timestamp.now())
)

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}