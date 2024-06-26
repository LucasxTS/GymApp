package com.example.gymapp.commons.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gymapp.ui.features.exercises.ExerciseScreen
import com.example.gymapp.ui.features.home.HomeScreen

@Composable
fun FlowView(
    navController: NavHostController,
    context: Context
) {
    NavHost(navController = navController, startDestination = routes.HOME.name) {
        composable(routes.HOME.name) {
            HomeScreen(navController)
        }
        composable(
            "${routes.EXERCISES.name}/{trainingName}",
            arguments = listOf(navArgument("trainingName") {type = androidx.navigation.NavType.StringType })
        ) {
            val trainingName = it.arguments?.getString("trainingName") ?: return@composable
            ExerciseScreen(navController, trainingName)
        }
    }
}