package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WorkoutActivity : AppCompatActivity() {

    private lateinit var workoutNameInput: EditText
    private lateinit var durationInput: EditText
    private lateinit var caloriesInput: EditText
    private lateinit var startButton: Button
    private lateinit var recordButton: Button
    private lateinit var workoutStatus: TextView
    private var isFirstTimeSetup = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        // Check if this is the first time setup
        val sharedPref = getSharedPreferences("WorkoutApp", MODE_PRIVATE)
        isFirstTimeSetup = !sharedPref.getBoolean("has_logged_workout", false)

        // Initialize views
        workoutNameInput = findViewById(R.id.workout_name_input)
        durationInput = findViewById(R.id.duration_input)
        caloriesInput = findViewById(R.id.calories_input)
        startButton = findViewById(R.id.start_button)
        recordButton = findViewById(R.id.record_button)
        workoutStatus = findViewById(R.id.workout_status)

        // If this is first time, show mandatory message
        if (isFirstTimeSetup) {
            workoutStatus.text = "⚠️ You must log a workout before using the app"
            recordButton.isEnabled = false
        }

        // Start workout button click listener
        startButton.setOnClickListener {
            val workoutName = workoutNameInput.text.toString()
            val duration = durationInput.text.toString()
            val calories = caloriesInput.text.toString()

            if (workoutName.isEmpty() || duration.isEmpty() || calories.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate duration and calories are numbers
            if (duration.toIntOrNull() == null || calories.toIntOrNull() == null) {
                Toast.makeText(this, "Duration and calories must be numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save workout
            saveWorkout(workoutName, duration.toInt(), calories.toInt())

            workoutStatus.text = "✓ Workout Logged: $workoutName"
            Toast.makeText(this, "Workout saved successfully!", Toast.LENGTH_SHORT).show()

            // Mark that workout has been logged
            val editor = sharedPref.edit()
            editor.putBoolean("has_logged_workout", true)
            editor.putLong("last_workout_time", System.currentTimeMillis())
            editor.apply()

            // If first time setup, redirect to main after a short delay
            if (isFirstTimeSetup) {
                Thread.sleep(1500)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // Record workout button click listener
        recordButton.setOnClickListener {
            startActivity(Intent(this, RecordingActivity::class.java))
        }
    }

    private fun saveWorkout(name: String, duration: Int, calories: Int) {
        val sharedPref = getSharedPreferences("WorkoutApp", MODE_PRIVATE)
        val editor = sharedPref.edit()
        
        // Get the current workout count and increment
        val workoutCount = sharedPref.getInt("workout_count", 0)
        editor.putInt("workout_count", workoutCount + 1)
        
        // Save this workout's details
        editor.putString("workout_${workoutCount}_name", name)
        editor.putInt("workout_${workoutCount}_duration", duration)
        editor.putInt("workout_${workoutCount}_calories", calories)
        editor.putLong("workout_${workoutCount}_time", System.currentTimeMillis())
        
        editor.apply()
    }

    override fun onBackPressed() {
        // If this is first time setup, don't allow back button
        if (isFirstTimeSetup) {
            Toast.makeText(this, "You must log a workout before leaving", Toast.LENGTH_SHORT).show()
            return
        }
        super.onBackPressed()
    }
}
