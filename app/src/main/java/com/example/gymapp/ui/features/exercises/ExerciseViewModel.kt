package com.example.gymapp.ui.features.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.domain.models.Exercise
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    private val _exercises = MutableStateFlow<List<Exercise?>>(emptyList())
    val exercises: StateFlow<List<Exercise?>> get() = _exercises

    fun fetchExercises(trainingName: String) {
        viewModelScope.launch {
            fireStore.collection("workouts").document(trainingName).collection("exercises").get()
                .addOnSuccessListener { result ->
                    val fetchedExercises =
                        result.documents.map { it.toObject(Exercise::class.java) }
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
                .document(exercise.name)
                .set(exercise)
                .addOnSuccessListener {
                    fetchExercises(trainingName)
                }
                .addOnFailureListener {
                    throw IllegalArgumentException("Failed to add Exercise", it)
                }
        }
    }
    fun editExercise(trainingName: String, oldExerciseName: String, updatedExercise: Exercise) {
        val exerciseRef =
            fireStore.collection("workouts").document(trainingName).collection("exercises")
        exerciseRef.document(oldExerciseName).delete().addOnSuccessListener {
            exerciseRef.document(updatedExercise.name).set(updatedExercise).addOnSuccessListener {
                fetchExercises(trainingName)
            }.addOnFailureListener {
                throw IllegalArgumentException("Failed to update Exercise", it)
            }
        }.addOnFailureListener {
            throw IllegalArgumentException("Failed to delete old Exercise", it)
        }
    }

    fun deleteExercise(trainingName: String, exercise: Exercise) {
        val fireStore =
            fireStore.collection("workouts").document(trainingName).collection("exercises")
                .document(exercise.name)
        fireStore.delete()
            .addOnSuccessListener {
                fetchExercises(trainingName)
            }
            .addOnFailureListener {
                throw IllegalArgumentException("Failed to delete exercise", it)
            }
    }
}