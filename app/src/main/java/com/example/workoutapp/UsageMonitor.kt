package com.example.workoutapp

import android.app.admin.DevicePolicyManager
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import java.util.*

class UsageMonitor(private val context: Context) {

    private val usageStatsManager =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    private val devicePolicyManager =
        context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("AppLimits", Context.MODE_PRIVATE)
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val USAGE_CHECK_INTERVAL = 60000L // Check every minute
        private const val TIME_LIMIT_MINUTES = 30
        private const val LOCK_DURATION_MINUTES = 120
    }

    fun startMonitoring() {
        handler.postDelayed(::checkAppUsage, USAGE_CHECK_INTERVAL)
    }

    fun stopMonitoring() {
        handler.removeCallbacks(::checkAppUsage)
    }

    fun addAppToMonitor(packageName: String) {
        val set = sharedPref.getStringSet("monitored_apps", mutableSetOf()) ?: mutableSetOf()
        set.add(packageName)
        sharedPref.edit().putStringSet("monitored_apps", set).apply()
    }

    fun removeAppFromMonitor(packageName: String) {
        val set = sharedPref.getStringSet("monitored_apps", mutableSetOf()) ?: mutableSetOf()
        set.remove(packageName)
        sharedPref.edit().putStringSet("monitored_apps", set).apply()
    }

    fun getMonitoredApps(): Set<String> {
        return sharedPref.getStringSet("monitored_apps", mutableSetOf()) ?: mutableSetOf()
    }

    private fun checkAppUsage() {
        val monitoredApps = getMonitoredApps()
        val cal = Calendar.getInstance()
        val endTime = cal.timeInMillis
        cal.add(Calendar.MINUTE, -TIME_LIMIT_MINUTES)
        val startTime = cal.timeInMillis

        for (packageName in monitoredApps) {
            val usageStats =
                usageStatsManager.queryAndAggregateUsageStats(startTime, endTime).filter {
                    it.key == packageName
                }

            for ((_, stats) in usageStats) {
                val usageMinutes = stats.totalTimeInForeground / 60000
                if (usageMinutes > TIME_LIMIT_MINUTES) {
                    // Lock the phone
                    lockPhoneForScreenTime(packageName)
                    break
                }
            }
        }

        // Continue monitoring
        handler.postDelayed(::checkAppUsage, USAGE_CHECK_INTERVAL)
    }

    private fun lockPhoneForScreenTime(packageName: String) {
        val sharedPref = context.getSharedPreferences("WorkoutApp", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putLong("screen_time_lock_time", System.currentTimeMillis())
        editor.putString("lock_reason", "Screen time exceeded for $packageName")
        editor.apply()

        // Show message and lock
        Toast.makeText(
            context,
            "Screen time limit reached! Phone locked for 2 hours. Go play!",
            Toast.LENGTH_LONG
        ).show()

        try {
            // Attempt to lock (requires device admin)
            devicePolicyManager.lockNow()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isPhoneLockedForScreenTime(): Boolean {
        val sharedPref = context.getSharedPreferences("WorkoutApp", Context.MODE_PRIVATE)
        val lockTime = sharedPref.getLong("screen_time_lock_time", 0)
        if (lockTime == 0L) return false

        val elapsedMinutes = (System.currentTimeMillis() - lockTime) / 60000
        return elapsedMinutes < LOCK_DURATION_MINUTES
    }
}
