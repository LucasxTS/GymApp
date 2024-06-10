package com.example.gymapp.ui.features.exercises

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gymapp.commons.navigation.routes


@Composable
fun ExerciseScreen(navController: NavController) {
    Button(onClick = { navController.navigate(routes.HOME.name) }) {

    }


    }

@Preview
@Composable
fun ExerciseScreenPreview() {

}