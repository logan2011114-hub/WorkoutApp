package com.example.workoutapp

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var usageMonitor: UsageMonitor
    private lateinit var appContainerView: LinearLayout
    private val packageManager by lazy { packageManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        usageMonitor = UsageMonitor(this)

        appContainerView = findViewById(R.id.app_container)
        val titleView = findViewById<TextView>(R.id.settings_title)
        val saveButton = findViewById<Button>(R.id.save_button)

        titleView.text = "Select Apps to Monitor (30 min limit = 2 hour lock)"
        titleView.textSize = 20f

        loadInstalledApps()

        saveButton.setOnClickListener {
            Toast.makeText(this, "Settings saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadInstalledApps() {
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val monitoredApps = usageMonitor.getMonitoredApps()

        for (app in apps) {
            if (app.flags and ApplicationInfo.FLAG_SYSTEM == 0) { // Non-system apps only
                val appName = packageManager.getApplicationLabel(app).toString()
                val packageName = app.packageName

                val checkBox = CheckBox(this)
                checkBox.text = appName
                checkBox.isChecked = monitoredApps.contains(packageName)
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        usageMonitor.addAppToMonitor(packageName)
                    } else {
                        usageMonitor.removeAppFromMonitor(packageName)
                    }
                }
                appContainerView.addView(checkBox)
            }
        }
    }
}
