package com.example.workoutapp

import android.content.Context
import android.content.Intent
import android.net.Uri

class YouTubeManager(private val context: Context) {

    companion object {
        private const val YOUTUBE_BASE_URL = "https://www.youtube.com/results?search_query="
    }

    fun openSydneyCummingsWorkout(workoutName: String) {
        val searchQuery = "Sydney Cummings $workoutName"
        val uri = Uri.parse(YOUTUBE_BASE_URL + Uri.encode(searchQuery))
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    fun getWorkoutList(): List<String> {
        return listOf(
            "30 Min Full Body HIIT",
            "20 Min Upper Body Strength",
            "25 Min Lower Body Burn",
            "15 Min Core and Abs",
            "30 Min Cardio Blast",
            "20 Min Yoga and Stretch",
            "25 Min EMOM Workout",
            "30 Min Bootcamp"
        )
    }
}
