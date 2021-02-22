package com.example.workoutmanager.add.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Firebase.firestore
    val error: MutableLiveData<String> = MutableLiveData()
    var stateAddWorkout: MutableLiveData<Boolean> = MutableLiveData()
    var stateAddExercise: MutableLiveData<Boolean> = MutableLiveData()

    fun addWorkout(name: String, description: String, date: String) {
        val workout = hashMapOf(
            "name" to name,
            "description" to description,
            "date" to date
        )
        db.collection("workouts")
            .add(workout)
            .addOnSuccessListener {
                stateAddWorkout.value = true
            }
            .addOnFailureListener {
                errorMessage("Failed to add.")
                stateAddWorkout.value = false
            }
    }

    fun addExercise(name: String, description: String) {
        val exercise = hashMapOf(
            "name" to name,
            "description" to description
        )
        db.collection("exercises")
            .add(exercise)
            .addOnSuccessListener {
                stateAddExercise.value = true
            }
            .addOnFailureListener {
                errorMessage("Failed to add.")
                stateAddExercise.value = false
            }
    }

    private fun errorMessage(message: String) {
        error.value = message
    }

}