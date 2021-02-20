package com.example.workoutmanager.add.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.workoutmanager.R
import com.example.workoutmanager.Util
import com.example.workoutmanager.add.viewmodel.AddViewModel
import com.example.workoutmanager.databinding.ActivityAddWorkoutBinding
import com.example.workoutmanager.home.view.HomeActivity
import com.google.android.material.snackbar.Snackbar

class AddWorkoutActivity : AppCompatActivity() {
    private val viewModel: AddViewModel by lazy {
        ViewModelProvider(this).get(
            AddViewModel::class.java
        )
    }
    private lateinit var binding: ActivityAddWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_workout)

        binding.addButton.setOnClickListener{
            val name = binding.inputName.text.toString()
            val description = binding.inputDescription.text.toString()
            val date = binding.inputDate.text.toString()

            when {
                Util.validateNameDescriptionAndDate(name, description, date) -> {
                    viewModel.addWorkout(name, description, date)
                }
                else -> {
                    Snackbar.make(binding.addButton,
                        "Empty fields not allowed.",
                        Snackbar.LENGTH_LONG).show()
                }
            }
        }
        binding.arrowBackIcon.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.stateAddWorkout.observe(this, { state ->
            state?.let {
                navigateToHome(it)
            }
        })

        viewModel.error.observe( this, { error ->
            error?.let {
                showErrorMessage(it)
            }
        })
    }

    private fun navigateToHome(status : Boolean) {
        when {
            status -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    private fun showErrorMessage(message : String) {
        Snackbar.make(binding.addButton, message, Snackbar.LENGTH_LONG).show()
    }
}