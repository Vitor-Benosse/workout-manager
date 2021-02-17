package com.example.workoutmanager.authentication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.workoutmanager.Util
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
    val error: MutableLiveData<String> = MutableLiveData()
    var stateLogin: MutableLiveData<Boolean> = MutableLiveData()
    var stateRegister: MutableLiveData<Boolean> = MutableLiveData()

    fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task: Task<AuthResult?> ->
                when {
                    task.isSuccessful -> {
                        Util.saveUserId(
                            getApplication(),
                            FirebaseAuth.getInstance().currentUser?.uid
                        )
                        stateLogin.value = true
                    }
                    else -> {
                        errorMessage("Authentication Failed")
                        stateLogin.value = false
                    }
                }
            }
    }

    fun register(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                when {
                    task.isSuccessful -> {
                        Util.saveUserId(
                            getApplication(),
                            FirebaseAuth.getInstance().currentUser?.uid
                        )
                        stateRegister.value = true
                    }
                    else -> {
                        errorMessage("Authentication Failed")
                        stateRegister.value = false
                    }
                }
            }
    }

    private fun errorMessage(message: String) {
        error.value = message
    }
}