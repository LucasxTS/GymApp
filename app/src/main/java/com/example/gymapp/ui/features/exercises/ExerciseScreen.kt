package com.example.gymapp.ui.features.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.gymapp.domain.models.Exercise
import com.example.gymapp.ui.components.exercise.AddExerciseDialog
import com.example.gymapp.ui.components.exercise.EditExerciseDialog
import com.example.gymapp.ui.components.exercise.ExerciseList
import com.example.gymapp.ui.components.home.FloatingActionButtonView


@Composable
fun ExerciseScreen(navController: NavController, trainingName: String, exerciseViewModel: ExerciseViewModel = viewModel()) {
        val exercises by exerciseViewModel.exercises.collectAsState()
        var showDialog by remember { mutableStateOf(false) }
        var editExercise by remember { mutableStateOf<Exercise?>(null) }
        var showEditDialog by remember { mutableStateOf(false) }


    LaunchedEffect(trainingName) {
        exerciseViewModel.fetchExercises(trainingName)

    }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ExerciseList(
                    exercises = exercises,
                    onDelete = {
                        exerciseViewModel.deleteExercise(trainingName, it)
                    }, onEdit =  {exercise ->
                        editExercise = exercise
                        showEditDialog = true
                    }
                )
            }
            FloatingActionButtonView { showDialog = true }
        }
    if (showDialog) {
        AddExerciseDialog(
            onDismissRequest = { showDialog = false },
            onConfirm = { name, observation, imageUrl ->
                val newExercise = Exercise(name = name, image = imageUrl, observation = observation)
                exerciseViewModel.addExercise(trainingName, newExercise)
            }
        )
    }
    if (showEditDialog && editExercise != null) {
        EditExerciseDialog(
            exercise = editExercise!!,
            onDismissRequest = {
                showEditDialog = false
                editExercise = null
            },
            onConfirm = { name, description, imageUrl ->
                val updatedExercise = Exercise(name = name, observation = description, image = imageUrl)
                exerciseViewModel.editExercise(trainingName, editExercise!!.name, updatedExercise)
                showEditDialog = false
                editExercise = null
            }
        )
    }
}

@Preview
@Composable
fun ExerciseScreenPreview() {
    ExerciseScreen(navController = rememberNavController(), "a")
}