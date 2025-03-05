package com.example.habit_tracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateNewHabitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_habit)

        val titleEditText: EditText = findViewById(R.id.titleEditText)
        val descriptionEditText: EditText = findViewById(R.id.descriptionEditText)
        val prioritySpinner: Spinner = findViewById(R.id.prioritySpinner)
        val typeRadioGroup: RadioGroup = findViewById(R.id.typeRadioGroup)
        val repetitionsEditText: EditText = findViewById(R.id.repetitionsEditText)
        val frequencySpinner: Spinner = findViewById(R.id.frequencySpinner)
        val saveButton: Button = findViewById(R.id.saveButton)

        val priorityAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        )
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = priorityAdapter

        val frequencyAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.frequency_array,
            android.R.layout.simple_spinner_item
        )
        frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        frequencySpinner.adapter = frequencyAdapter

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()
            val priority = prioritySpinner.selectedItem.toString()

            val selectedTypeId = typeRadioGroup.checkedRadioButtonId
            val type = when (selectedTypeId) {
                R.id.healthRadioButton -> "Здоровье"
                R.id.workRadioButton -> "Работа"
                R.id.sportRadioButton -> "Спорт"
                R.id.otherRadioButton -> "Другое"
                else -> ""
            }

            val repetitionsText = repetitionsEditText.text.toString().trim()
            val repetitions = if (repetitionsText.isNotEmpty()) repetitionsText.toInt() else 0

            val frequency = frequencySpinner.selectedItem.toString()

            if (title.isNotEmpty() && type.isNotEmpty() && repetitions > 0) {
                val habit = Habit(title, description, priority, type, repetitions, frequency)

                Log.d("CreateNewHabitActivity", "Created habit: $habit")

                val resultIntent = Intent()
                resultIntent.putExtra(MainActivity.EXTRA_HABIT, habit)

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Заполните название, тип и количество повторений",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }
}