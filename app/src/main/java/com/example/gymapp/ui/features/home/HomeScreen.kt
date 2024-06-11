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
import com.example.gymapp.commons.navigation.CreateRoute
import com.example.gymapp.domain.models.Training
import com.example.gymapp.ui.components.home.AddTrainingDialog
import com.example.gymapp.ui.components.home.EditTrainingDialog
import com.example.gymapp.ui.components.home.FloatingActionButtonView
import com.example.gymapp.ui.components.home.HomeHeader
import com.example.gymapp.ui.components.home.TrainingList
import com.google.firebase.Timestamp

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var trainingToEdit by remember { mutableStateOf<Training?>(null) }
    val treinos by homeViewModel.treinos.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            HomeHeader()
            TrainingList(
                treinos,
                viewModel = homeViewModel,
                onItemClick = { trainingName ->
                    navController.navigate(CreateRoute(trainingName))
                },
                onEdit = { training ->
                    trainingToEdit = training
                    showEditDialog = true
                },
                onDelete = { training ->
                    homeViewModel.deleteTraining(training)
                }
            )
        }
        FloatingActionButtonView { showCreateDialog = true }
    }

    if (showCreateDialog) {
        AddTrainingDialog(
            onDismissRequest = { showCreateDialog = false },
            onConfirm = { name, description ->
                homeViewModel.createNewTraining(name, description)
            }
        )
    }

    if (showEditDialog && trainingToEdit != null) {
        EditTrainingDialog(
            training = trainingToEdit!!,
            onDismissRequest = { showEditDialog = false },
            onConfirm = { name, description ->
                homeViewModel.editTraining(trainingToEdit!!, Training(name, description, trainingToEdit!!.date))
                showEditDialog = false
            }
        )
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}