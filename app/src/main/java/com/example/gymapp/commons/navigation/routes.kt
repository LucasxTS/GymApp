package com.example.gymapp.commons.navigation

enum class routes {
    HOME,
    EXERCISES
}

fun CreateRoute(trainingName: String): String {
    return "${routes.EXERCISES.name}/$trainingName"
}