package com.example.workoutapp

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var devicePolicyManager: DevicePolicyManager
    private lateinit var componentName: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)

        // Check if it's a new day
        val sharedPref = getSharedPreferences("WorkoutApp", MODE_PRIVATE)
        val lastWorkoutDate = sharedPref.getString("last_workout_date", "")
        val today = getCurrentDate()

        // If it's a new day, lock the screen and show workout selection
        if (lastWorkoutDate != today) {
            // Lock the screen
            lockScreen()
            // Navigate to workout selection
            startActivity(Intent(this, WorkoutSelectionActivity::class.java))
            finish()
        } else {
            // User already worked out today, show main UI
            showMainUI()
        }
    }

    private fun lockScreen() {
        try {
            if (devicePolicyManager.isAdminActive(componentName)) {
                devicePolicyManager.lockNow()
            } else {
                Toast.makeText(this, "Please enable Device Admin", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showMainUI() {
        // Display home screen UI with time tracking options
        Toast.makeText(this, "Welcome! You've completed your workout today.", Toast.LENGTH_SHORT).show()
    }

    private fun getCurrentDate(): String {
        val cal = Calendar.getInstance()
        return "${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)}-${cal.get(Calendar.DAY_OF_MONTH)}"
    }
}
