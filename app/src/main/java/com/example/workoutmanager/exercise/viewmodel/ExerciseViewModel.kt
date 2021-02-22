package com.example.workoutmanager.exercise.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.workoutmanager.Util
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Firebase.firestore

    fun deleteExercise(name: String? ,workoutName: String?) {
        if (workoutName != null && name != null) {
                db.collection(Util.getUserId(getApplication()).toString())
                    .document(workoutName).collection("exercises")
                    .document(name).delete()
        }
    }
}