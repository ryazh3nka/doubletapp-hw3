package com.example.habit_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitsAdapter(private val habits: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val priorityTextView: TextView = itemView.findViewById(R.id.priorityTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val repetitionsTextView: TextView = itemView.findViewById(R.id.repetitionsTextView)
        val frequencyTextView: TextView = itemView.findViewById(R.id.frequencyTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.titleTextView.text = habit.title
        holder.descriptionTextView.text = "Описание: ${habit.description}"
        holder.priorityTextView.text = "Приоритет: ${habit.priority}"
        holder.typeTextView.text = "Тип: ${habit.type}"
        holder.repetitionsTextView.text = "Повторений: ${habit.repetitions}"
        holder.frequencyTextView.text = "Периодичность: ${habit.frequency}"
    }

    override fun getItemCount(): Int = habits.size
}