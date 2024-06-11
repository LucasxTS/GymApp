package com.example.gymapp.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.domain.models.Training
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    private val _treinos = MutableStateFlow<List<Training?>>(emptyList())
    val treinos: StateFlow<List<Training?>> get() = _treinos

    init {
        fetchTraining()
    }
     private fun fetchTraining() {
        viewModelScope.launch {
            fireStore.collection("workouts").get()
                .addOnSuccessListener { result ->
                    val fetchedTrainings = result.documents.map { it.toObject(Training::class.java) }
                        _treinos.value = fetchedTrainings

                }
                .addOnFailureListener {
                    throw IllegalArgumentException("Failed to add training", it)
                }
        }
    }
    fun addTraining(training: Training) {
        viewModelScope.launch {
            fireStore.collection("workouts").add(training)
                .addOnSuccessListener {
                    fetchTraining()
                }
                .addOnFailureListener {
                    throw IllegalArgumentException("Failed to add training", it)
                }
        }
    }
    fun deleteTraining(index: Int) {
        val currentList = _treinos.value.toMutableList()
        if (index in currentList.indices) {
            currentList.removeAt(index)
            _treinos.value = currentList
            fetchTraining()
        }
    }
    fun editTraining(oldTraining: Training,  updatedTraining: Training) {
        fireStore.collection("workouts").document(oldTraining.name).update(
            mapOf(
                oldTraining.name to updatedTraining.name
            )
        )
        fetchTraining()
    }
}