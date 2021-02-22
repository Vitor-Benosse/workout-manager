package com.example.workoutmanager.exercise.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.workoutmanager.R
import com.example.workoutmanager.databinding.ActivityExerciseBinding
import com.example.workoutmanager.exercise.viewmodel.ExerciseViewModel
import com.example.workoutmanager.home.view.HomeActivity

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise)
        val viewModel: ExerciseViewModel by lazy {
            ViewModelProvider(this).get(
                ExerciseViewModel::class.java
            )
        }

        binding.toolbarWorkoutName.text = intent.getStringExtra(EXTRA_NAME)
        binding.exerciseDescriptionText.text = intent.getStringExtra(EXTRA_DESCRIPTION)

        binding.arrowBackIcon.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.deleteIcon.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to Delete the entire workout?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteExercise(
                        intent.getStringExtra(EXTRA_NAME),
                        intent.getStringExtra(EXTRA_WORKOUT_NAME)
                    )
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        const val EXTRA_WORKOUT_NAME = "EXTRA_WORKOUT_NAME"

        fun getStartIntent(
            context: Context,
            name: String?,
            description: String?,
            workoutName: String?
        ): Intent {
            return Intent(context, ExerciseActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_DESCRIPTION, description)
                putExtra(EXTRA_WORKOUT_NAME, workoutName)
            }
        }
    }
}