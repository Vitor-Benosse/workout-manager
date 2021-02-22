package com.example.workoutmanager.add.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.workoutmanager.R
import com.example.workoutmanager.Util
import com.example.workoutmanager.add.viewmodel.AddViewModel
import com.example.workoutmanager.databinding.ActivityAddExerciseBinding
import com.example.workoutmanager.workout.view.WorkoutActivity
import com.google.android.material.snackbar.Snackbar

class AddExerciseActivity : AppCompatActivity() {
    private val viewModel: AddViewModel by lazy {
        ViewModelProvider(this).get(
            AddViewModel::class.java
        )
    }
    private lateinit var binding: ActivityAddExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_exercise)

        binding.addButton.setOnClickListener{
            val name = binding.inputName.text.toString()
            val description = binding.inputDescription.text.toString()

            when {
                Util.validateNameAndDescription(name, description) -> {
                    viewModel.addExercise(name, description)
                }
                else -> {
                    Snackbar.make(binding.addButton,
                        "Empty fields not allowed.",
                        Snackbar.LENGTH_LONG).show()
                }
            }
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.stateAddExercise.observe(this, { state ->
            state?.let {
                navigateToWorkout(it)
            }
        })

        viewModel.error.observe( this, { error ->
            error?.let {
                showErrorMessage(it)
            }
        })
    }

    private fun navigateToWorkout(status : Boolean) {
        when {
            status -> {
                startActivity(Intent(this, WorkoutActivity::class.java))
            }
        }
    }

    private fun showErrorMessage(message : String) {
        Snackbar.make(binding.addButton, message, Snackbar.LENGTH_LONG).show()
    }
}