package com.example.workoutmanager.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutmanager.R
import com.example.workoutmanager.model.Workout
import kotlinx.android.synthetic.main.item_workout.view.*

class WorkoutAdapter(
    private val workouts: List<Workout>
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bindView(workouts[position])
    }

    override fun getItemCount() = workouts.count()

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.workout_name
        private val description = itemView.workout_description
        private val date = itemView.workout_date

        fun bindView(workout: Workout) {
            name.text = workout.name.toString()
            description.text = workout.description
            date.text = workout.date.toString()
        }
    }
}