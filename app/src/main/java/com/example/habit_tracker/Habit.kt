package com.example.habit_tracker

import java.io.Serializable

data class Habit(
    val title: String,
    val description: String,
    val priority: String,
    val type: String,
    val repetitions: Int,
    val frequency: String
) : Serializable