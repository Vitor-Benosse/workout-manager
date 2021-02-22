package com.example.workoutmanager.workout.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutmanager.R
import com.example.workoutmanager.add.view.AddExerciseActivity
import com.example.workoutmanager.databinding.ActivityWorkoutBinding
import com.example.workoutmanager.exercise.view.ExerciseActivity
import com.example.workoutmanager.home.view.HomeActivity
import com.example.workoutmanager.workout.adapter.ExerciseAdapter
import com.example.workoutmanager.workout.viewmodel.WorkoutViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_workout.*

class WorkoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_workout)
        val viewModel: WorkoutViewModel by lazy {
            ViewModelProvider(this).get(
                WorkoutViewModel::class.java
            )
        }

        binding.toolbarWorkoutName.text = intent.getStringExtra(EXTRA_NAME)

        binding.arrowBackIcon.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.deleteIcon.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to Delete the entire workout?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteWorkout(intent.getStringExtra(EXTRA_NAME))
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        viewModel.exerciseLiveData.observe(this, Observer {
            it?.let { exercises ->
                with(recyclerExercises) {
                    layoutManager = LinearLayoutManager(this@WorkoutActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = ExerciseAdapter(exercises) { exercise ->
                        val intent = ExerciseActivity.getStartIntent(
                            this@WorkoutActivity,
                            exercise.name,
                            exercise.description,
                            intent.getStringExtra(EXTRA_NAME)
                        )
                        this@WorkoutActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.getExercises(intent.getStringExtra(EXTRA_NAME))

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddExerciseActivity::class.java).putExtra("EXTRA_NAME", intent.getStringExtra(EXTRA_NAME)))
        }
    }

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"

        fun getStartIntent(context: Context, name: String?): Intent {
            return Intent(context, WorkoutActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
            }
        }
    }
}