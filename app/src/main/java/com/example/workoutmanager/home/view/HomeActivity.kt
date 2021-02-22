package com.example.workoutmanager.home.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutmanager.R
import com.example.workoutmanager.add.view.AddWorkoutActivity
import com.example.workoutmanager.databinding.ActivityHomeBinding
import com.example.workoutmanager.home.adapter.WorkoutAdapter
import com.example.workoutmanager.home.viewmodel.HomeViewModel
import com.example.workoutmanager.workout.view.WorkoutActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val viewModel: HomeViewModel by lazy {
            ViewModelProvider(this).get(
                HomeViewModel::class.java
            )
        }
        viewModel.workoutLiveData.observe(this, Observer {
            it?.let { workouts ->
                with(recyclerWorkouts) {
                    layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = WorkoutAdapter(workouts) { workout ->
                        val intent = WorkoutActivity.getStartIntent(this@HomeActivity, workout.name)
                        this@HomeActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.getWorkouts()

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddWorkoutActivity::class.java))
        }
    }
}