package com.example.workoutapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class MotionDetector(context: Context) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private var lastMotionTime = System.currentTimeMillis()
    private var motionThreshold = 15f // Threshold for motion detection

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun detectMotion(): Boolean {
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastMotionTime) < 5000 // Motion detected in last 5 seconds
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = it.values[0]
            val y = it.values[1]
            val z = it.values[2]
            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()

            // If acceleration exceeds threshold, consider it motion
            if (acceleration > motionThreshold) {
                lastMotionTime = System.currentTimeMillis()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun unregister() {
        sensorManager.unregisterListener(this)
    }
}
