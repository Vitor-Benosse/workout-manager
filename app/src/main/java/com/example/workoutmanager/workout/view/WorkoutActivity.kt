package com.example.workoutmanager.workout.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.workoutmanager.R
import com.example.workoutmanager.add.view.AddExerciseActivity
import com.example.workoutmanager.databinding.ActivityWorkoutBinding
import com.example.workoutmanager.home.view.HomeActivity

class WorkoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_workout)

        binding.toolbarWorkoutName.text = intent.getStringExtra(EXTRA_NAME)

        binding.arrowBackIcon.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddExerciseActivity::class.java))
        }
    }

    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"

        fun getStartIntent(context: Context, name: String?): Intent {
            return Intent(context, WorkoutActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
            }
        }
    }
}