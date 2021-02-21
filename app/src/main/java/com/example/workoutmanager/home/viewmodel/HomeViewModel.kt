package com.example.workoutmanager.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.workoutmanager.model.Workout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Firebase.firestore
    val workoutLiveData : MutableLiveData<List<Workout>> = MutableLiveData()

    fun getWorkoutsReal() {
        val workouts: MutableList<Workout> = mutableListOf()
        db.collection("workouts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val workout = Workout(
                        name = document.getString("name"),
                        description = document.getString("description"),
                        date = document.getString("date")
                    )
                    workouts.add(workout)
                }
                workoutLiveData.value = workouts
            }
            .addOnFailureListener { exception ->
                Log.w("Failure", "Error getting documents.", exception)
            }
    }
}