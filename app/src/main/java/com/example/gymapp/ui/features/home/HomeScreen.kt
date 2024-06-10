package com.example.gymapp.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.domain.models.Training
import com.example.gymapp.ui.components.home.AddTrainingDialog
import com.example.gymapp.ui.components.home.FloatingActionButtonView
import com.example.gymapp.ui.components.home.HomeHeader
import com.example.gymapp.ui.components.home.TrainingList
import com.google.firebase.Timestamp

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    val treinos by homeViewModel.treinos.collectAsState()

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
            TrainingList(treinos, viewModel = homeViewModel)
    }
        FloatingActionButtonView { showDialog = true }
    }

    if (showDialog) {
        AddTrainingDialog(
            onDismissRequest = { showDialog = false },
            onConfirm = { name, description ->
                val newTraining = Training(name = name, description = description, date = Timestamp.now())
                homeViewModel.addTraining(newTraining)
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}