package com.example.workoutmanager.workout.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.workoutmanager.model.Exercise
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Firebase.firestore
    val exerciseLiveData : MutableLiveData<List<Exercise>> = MutableLiveData()

    fun getExercises() {
        val exercises: MutableList<Exercise> = mutableListOf()
        db.collection("exercises")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val exercise = Exercise(
                        name = document.getString("name"),
                        description = document.getString("description"),
                    )
                    exercises.add(exercise)
                }
                exerciseLiveData.value = exercises
            }
            .addOnFailureListener { exception ->
                Log.w("Failure", "Error getting documents.", exception)
            }
    }
}