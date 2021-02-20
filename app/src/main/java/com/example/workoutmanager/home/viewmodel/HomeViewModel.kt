package com.example.workoutmanager.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.workoutmanager.model.Workout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Firebase.firestore
    val workoutLiveData : MutableLiveData<List<Workout>> = MutableLiveData()

    fun getWorkouts() {
        val workouts: MutableList<Workout> = mutableListOf()
        db.collection("workouts").get()
            .addOnSuccessListener { results ->
                for (document in results) {
                    val workout = Workout(
                        name = document.getString("name"),
                        description = document.getString("description"),
                        date = document.getString("date")
                    )
                    workouts.add(workout)
                }
            }
    }
}