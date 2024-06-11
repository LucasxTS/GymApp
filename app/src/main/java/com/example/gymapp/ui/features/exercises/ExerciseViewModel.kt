package com.example.gymapp.ui.features.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.domain.models.Exercise
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.URI
import java.net.URL

class ExerciseViewModel : ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    private val _exercises = MutableStateFlow<List<Exercise?>>(emptyList())
    val exercises: StateFlow<List<Exercise?>> get() = _exercises

    fun fetchExercises(trainingName: String) {
        viewModelScope.launch {
            fireStore.collection("workouts").document(trainingName).collection("exercises").get()
                .addOnSuccessListener { result ->
                    val fetchedExercises = result.documents.map { it.toObject(Exercise::class.java) }
                    _exercises.value = fetchedExercises
                }
                .addOnFailureListener {
                    throw IllegalArgumentException("Failed to get exercises", it)
                }
        }
    }

    fun addExercise(trainingName: String, exercise: Exercise) {
        viewModelScope.launch {
            fireStore.collection("workouts").document(trainingName).collection("exercises")
                .add(exercise)
                .addOnSuccessListener {
                    fetchExercises(trainingName)
                }
                .addOnFailureListener {
                    throw IllegalArgumentException("Failed to add Exercise", it)
                }
        }
    }

    fun StringToUrl(stringURL: String): URL {
        return URL(stringURL)
    }
}