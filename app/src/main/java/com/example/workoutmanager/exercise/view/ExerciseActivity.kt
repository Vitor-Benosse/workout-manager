package com.example.workoutmanager.exercise.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.workoutmanager.R
import com.example.workoutmanager.databinding.ActivityExerciseBinding
import com.example.workoutmanager.workout.view.WorkoutActivity

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise)

        binding.toolbarWorkoutName.text = intent.getStringExtra(EXTRA_NAME)
        binding.exerciseDescriptionText.text = intent.getStringExtra(EXTRA_DESCRIPTION)

        binding.arrowBackIcon.setOnClickListener{
            startActivity(Intent(this, WorkoutActivity::class.java))
        }
    }

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, name: String?, description: String?): Intent {
            return Intent(context, ExerciseActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}