package com.example.gymapp.ui.features.createtraining

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gymapp.commons.navigation.routes

@Composable
fun CreateTrainingScreen(navController: NavController) {
    Button(onClick = { navController.navigate(routes.EXERCICES.name) }) {
        Text(text = "TELA 2 VIADOOOOO")
    }

    }

@Preview
@Composable
fun CreateTrainingScreenPreview() {

}