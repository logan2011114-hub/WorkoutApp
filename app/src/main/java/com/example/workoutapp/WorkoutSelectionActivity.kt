package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WorkoutSelectionActivity : AppCompatActivity() {

    // 50+ Sydney Cummings workout videos
    private val sydneyCummingsWorkouts = listOf(
        // Full Body Workouts
        "30 Min Full Body HIIT" to "https://www.youtube.com/embed/...",
        "45 Min Full Body Strength" to "https://www.youtube.com/embed/...",
        "25 Min Full Body Burn" to "https://www.youtube.com/embed/...",
        "35 Min Full Body Bootcamp" to "https://www.youtube.com/embed/...",
        "22 Min Full Body Strength" to "https://www.youtube.com/embed/...",
        "28 Min Full Body Toning" to "https://www.youtube.com/embed/...",
        
        // Upper Body Workouts
        "20 Min Upper Body Strength" to "https://www.youtube.com/embed/...",
        "25 Min Arm & Shoulder Blast" to "https://www.youtube.com/embed/...",
        "30 Min Upper Body HIIT" to "https://www.youtube.com/embed/...",
        "15 Min Quick Upper Body" to "https://www.youtube.com/embed/...",
        "35 Min Back & Biceps" to "https://www.youtube.com/embed/...",
        "18 Min Chest & Triceps" to "https://www.youtube.com/embed/...",
        
        // Lower Body Workouts
        "25 Min Lower Body Burn" to "https://www.youtube.com/embed/...",
        "30 Min Leg Day" to "https://www.youtube.com/embed/...",
        "20 Min Glute & Quad Workout" to "https://www.youtube.com/embed/...",
        "35 Min Lower Body Strength" to "https://www.youtube.com/embed/...",
        "22 Min Glute Focused" to "https://www.youtube.com/embed/...",
        "28 Min Leg Burner" to "https://www.youtube.com/embed/...",
        
        // Core & Abs Workouts
        "15 Min Core and Abs" to "https://www.youtube.com/embed/...",
        "20 Min Abs Burner" to "https://www.youtube.com/embed/...",
        "10 Min Quick Core" to "https://www.youtube.com/embed/...",
        "25 Min Full Abs Workout" to "https://www.youtube.com/embed/...",
        "30 Min Core Strength" to "https://www.youtube.com/embed/...",
        
        // Cardio Workouts
        "30 Min Cardio Blast" to "https://www.youtube.com/embed/...",
        "20 Min HIIT Cardio" to "https://www.youtube.com/embed/...",
        "25 Min Jump Rope Cardio" to "https://www.youtube.com/embed/...",
        "40 Min Steady State Cardio" to "https://www.youtube.com/embed/...",
        "18 Min Express Cardio" to "https://www.youtube.com/embed/...",
        "35 Min Fat Burner" to "https://www.youtube.com/embed/...",
        
        // Flexibility & Recovery
        "20 Min Yoga and Stretch" to "https://www.youtube.com/embed/...",
        "15 Min Cool Down Stretch" to "https://www.youtube.com/embed/...",
        "25 Min Yoga Flow" to "https://www.youtube.com/embed/...",
        "30 Min Restorative Yoga" to "https://www.youtube.com/embed/...",
        "12 Min Quick Stretch" to "https://www.youtube.com/embed/...",
        
        // Specialized Workouts
        "25 Min EMOM Workout" to "https://www.youtube.com/embed/...",
        "30 Min Bootcamp" to "https://www.youtube.com/embed/...",
        "20 Min AMRAP Workout" to "https://www.youtube.com/embed/...",
        "35 Min Circuit Training" to "https://www.youtube.com/embed/...",
        "15 Min Quick Workout" to "https://www.youtube.com/embed/...",
        "45 Min Advanced Strength" to "https://www.youtube.com/embed/...",
        "20 Min Cardio + Strength Combo" to "https://www.youtube.com/embed/...",
        "28 Min Metabolic Conditioning" to "https://www.youtube.com/embed/...",
        "32 Min Endurance Builder" to "https://www.youtube.com/embed/...",
        "40 Min Power Hour" to "https://www.youtube.com/embed/...",
        "22 Min Total Body HIIT" to "https://www.youtube.com/embed/...",
        "26 Min Functional Fitness" to "https://www.youtube.com/embed/...",
        "34 Min Plyometrics" to "https://www.youtube.com/embed/...",
        "19 Min Boxing Cardio" to "https://www.youtube.com/embed/...",
        "23 Min Pilates Strength" to "https://www.youtube.com/embed/...",
        "38 Min Total Body Challenge" to "https://www.youtube.com/embed/..."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_selection)

        val titleView = findViewById<TextView>(R.id.workout_title)
        val containerView = findViewById<LinearLayout>(R.id.workout_container)

        titleView.text = "Choose Your Workout\n(${sydneyCummingsWorkouts.size} options)\nby Sydney Cummings"
        titleView.textSize = 24f

        // Create buttons for each workout
        for ((workoutName, _) in sydneyCummingsWorkouts) {
            val button = Button(this)
            button.text = workoutName
            button.textSize = 14f
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
