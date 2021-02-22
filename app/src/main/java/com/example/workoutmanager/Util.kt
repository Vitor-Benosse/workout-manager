package com.example.workoutmanager

import android.content.Context
import android.util.Patterns
import com.example.workoutmanager.Constants.APP_KEY
import com.example.workoutmanager.Constants.EMPTY_STRING
import com.example.workoutmanager.Constants.UIID_KEY

object Util {
    fun validateEmailAndPassword(email: String, password: String) : Boolean {
        return when {
            email.isEmpty() || password.isEmpty() -> {
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                false
            }
            password.length < 6 -> {
                false
            }
            else -> true
        }
    }

    fun validateNameEmailAndPassword(name: String, email: String, password: String) : Boolean {
        return when {
            email.isEmpty() || password.isEmpty() || name.isEmpty() -> {
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                false
            }
            password.length < 6 -> {
                false
            }
            else -> true
        }
    }

    fun validateNameDescriptionAndDate(name: String, description: String, date: String) : Boolean {
        return when {
            name.isEmpty() || description.isEmpty() || date.isEmpty() -> {
                false
            }
            else -> true
        }
    }

    fun validateNameAndDescription(name: String, description: String) : Boolean {
        return when {
            name.isEmpty() || description.isEmpty() -> {
                false
            }
            else -> true
        }
    }


    fun saveUserId(context: Context, uiid: String?) {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        preferences.edit().putString(UIID_KEY, uiid).apply()
    }

    fun getUserId(context: Context): String? {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        return preferences.getString(UIID_KEY, EMPTY_STRING)
    }
}