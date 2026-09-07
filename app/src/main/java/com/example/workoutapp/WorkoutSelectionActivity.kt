package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WorkoutSelectionActivity : AppCompatActivity() {

    // Sydney Cummings workout videos
    private val sydneyCummingsWorkouts = listOf(
        "30 Min Full Body HIIT" to "https://www.youtube.com/embed/...",
        "20 Min Upper Body Strength" to "https://www.youtube.com/embed/...",
        "25 Min Lower Body Burn" to "https://www.youtube.com/embed/...",
        "15 Min Core and Abs" to "https://www.youtube.com/embed/...",
        "30 Min Cardio Blast" to "https://www.youtube.com/embed/...",
        "20 Min Yoga and Stretch" to "https://www.youtube.com/embed/...",
        "25 Min EMOM Workout" to "https://www.youtube.com/embed/...",
        "30 Min Bootcamp" to "https://www.youtube.com/embed/..."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_selection)

        val titleView = findViewById<TextView>(R.id.workout_title)
        val containerView = findViewById<LinearLayout>(R.id.workout_container)

        titleView.text = "Choose Your Workout"
        titleView.textSize = 28f

        // Create buttons for each workout
        for ((workoutName, _) in sydneyCummingsWorkouts) {
            val button = Button(this)
            button.text = workoutName
            button.textSize = 16f
            button.setPadding(20, 20, 20, 20)
            button.setOnClickListener {
                startWorkout(workoutName)
            }
            containerView.addView(button)
        }
    }

    private fun startWorkout(workoutName: String) {
        val intent = Intent(this, RecordingActivity::class.java)
        intent.putExtra("workout_name", workoutName)
        startActivity(intent)
        finish()
    }
}
