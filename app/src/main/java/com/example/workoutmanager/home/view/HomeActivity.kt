package com.example.workoutmanager.home.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.workoutmanager.R
import com.example.workoutmanager.add.view.AddWorkoutActivity
import com.example.workoutmanager.databinding.ActivityHomeBinding
import com.example.workoutmanager.home.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(
            HomeViewModel::class.java
        )
    }
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddWorkoutActivity::class.java))
        }
    }
}