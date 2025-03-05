package com.example.habit_tracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val habitsList = mutableListOf<Habit>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HabitsAdapter

    private val addHabitLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { data ->
                val habit = data.getSerializableExtra(EXTRA_HABIT) as? Habit
                habit?.let {
                    habitsList.add(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HabitsAdapter(habitsList)
        recyclerView.adapter = adapter

        val buttonTakeToHabitCreation = findViewById<FloatingActionButton>(R.id.fab)
        buttonTakeToHabitCreation.setOnClickListener {
            val intentOnClick = Intent(this, CreateNewHabitActivity::class.java)
            addHabitLauncher.launch(intentOnClick)
        }
    }

    companion object {
        const val EXTRA_HABIT = "extra_habit"
    }
}