package com.example.gymapp.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.domain.models.Training
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
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
            fireStore.collection("workouts").document(training.name).set(training)
                .addOnSuccessListener {
                    fetchTraining()
                }
                .addOnFailureListener {
                    throw IllegalArgumentException("Failed to add training", it)
                }
        }
    }
    fun deleteTraining(training: Training) {
        val fireStore = fireStore.collection("workouts")
        fireStore.document(training.name).delete()
            .addOnSuccessListener {
                fetchTraining()
            }
            .addOnFailureListener {
                throw IllegalArgumentException("Failed to delete training", it)
            }
    }
    fun editTraining(oldTraining: Training, updatedTraining: Training) {
        viewModelScope.launch {
            val _fireStore = fireStore.collection("workouts")
            val oldTrainingDoc = _fireStore.document(oldTraining.name)
            val newTrainingDoc = _fireStore.document(updatedTraining.name)

            newTrainingDoc.set(updatedTraining)
                .addOnSuccessListener {
                    oldTrainingDoc.collection("exercises").get()
                        .addOnSuccessListener { result ->
                            val batch = fireStore.batch()
                            for (document in result) {
                                val newExerciseDoc = newTrainingDoc.collection("exercises").document(document.id)
                                batch.set(newExerciseDoc, document.data)
                            }
                            batch.commit()
                                .addOnSuccessListener {
                                    deleteDocumentWithSubcollections(oldTrainingDoc)
                                }
                                .addOnFailureListener { batchError ->
                                    throw IllegalArgumentException("Failed to copy exercises", batchError)
                                }
                        }
                        .addOnFailureListener { subCollectionError ->
                            throw IllegalArgumentException("Failed to get exercises", subCollectionError)
                        }
                }
                .addOnFailureListener { copyError ->
                    throw IllegalArgumentException("Failed to create new training document", copyError)
                }
        }
    }

    private fun deleteDocumentWithSubcollections(docRef: DocumentReference) {
        docRef.collection("exercises").get()
            .addOnSuccessListener { result ->
                val batch = fireStore.batch()
                for (document in result) {
                    batch.delete(document.reference)
                }
                batch.commit()
                    .addOnSuccessListener {
                        docRef.delete()
                            .addOnSuccessListener {
                                println("Old document deleted successfully")
                                fetchTraining()
                            }
                            .addOnFailureListener { deleteError ->
                                throw IllegalArgumentException("Failed to delete old training", deleteError)
                            }
                    }
                    .addOnFailureListener { batchError ->
                        throw IllegalArgumentException("Failed to delete exercises", batchError)
                    }
            }
            .addOnFailureListener { subCollectionError ->
                throw IllegalArgumentException("Failed to get exercises", subCollectionError)
            }
    }


    fun createNewTraining(name: String, description: String) {
        val newTraining = Training(name = name, description = description, date = Timestamp.now())
        addTraining(newTraining)
    }
}