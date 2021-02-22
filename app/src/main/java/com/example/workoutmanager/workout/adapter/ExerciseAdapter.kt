package com.example.workoutmanager.workout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutmanager.R
import com.example.workoutmanager.model.Exercise
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExerciseAdapter(
    private val exercises: List<Exercise>,
    private val onItemClickListener: ((exercise: Exercise) -> Unit)
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(viewHolder: ExerciseViewHolder, position: Int) {
        viewHolder.bindView(exercises[position])
    }

    override fun getItemCount() = exercises.count()

    class ExerciseViewHolder(
        itemView: View,
        private val onItemClickListener: ((exercise: Exercise) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.exercise_name
        private val description = itemView.exercise_description

        fun bindView(exercise: Exercise) {
            name.text = exercise.name
            description.text = exercise.description

            itemView.setOnClickListener {
                onItemClickListener.invoke(exercise)
            }
        }
    }
}