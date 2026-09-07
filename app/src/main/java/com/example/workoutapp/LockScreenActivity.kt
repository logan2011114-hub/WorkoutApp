package com.example.workoutapp

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LockScreenActivity : AppCompatActivity() {

    private lateinit var devicePolicyManager: DevicePolicyManager
    private lateinit var componentName: ComponentName
    private lateinit var usageMonitor: UsageMonitor
    private lateinit var messageView: TextView
    private lateinit var unlockButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)
        usageMonitor = UsageMonitor(this)

        messageView = findViewById(R.id.lock_message)
        unlockButton = findViewById(R.id.unlock_button)

        val sharedPref = getSharedPreferences("WorkoutApp", MODE_PRIVATE)
        val lockReason = sharedPref.getString("lock_reason", "")

        if (lockReason.contains("Screen time")) {
            messageView.text = "Go play! Your screen time limit has been reached.\n\nPhone will unlock in 2 hours."
            unlockButton.isEnabled = false
        } else {
            messageView.text = "Complete your daily workout to unlock your phone!"
            unlockButton.text = "Go to Workout"
            unlockButton.setOnClickListener {
                startActivity(Intent(this, WorkoutSelectionActivity::class.java))
            }
        }
    }
}
