package com.example.workoutapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.media.MediaRecorder
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.SurfaceView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException

class RecordingActivity : AppCompatActivity() {

    private var camera: Camera? = null
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var surfaceView: SurfaceView
    private lateinit var timerView: TextView
    private lateinit var stopButton: Button
    private var isRecording = false
    private var workoutName = ""
    private var workoutDuration = 0L
    private var userActivity = false
    private lateinit var motionDetector: MotionDetector

    companion object {
        private const val CAMERA_PERMISSION_REQUEST = 1001
        private const val AUDIO_PERMISSION_REQUEST = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recording)

        workoutName = intent.getStringExtra("workout_name") ?: "Unknown Workout"
        workoutDuration = parseWorkoutDuration(workoutName) * 60000 // Convert to milliseconds

        surfaceView = findViewById(R.id.surface_view)
        timerView = findViewById(R.id.timer_view)
        stopButton = findViewById(R.id.stop_button)

        // Initialize motion detector
        motionDetector = MotionDetector(this)

        // Request permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST
            )
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                AUDIO_PERMISSION_REQUEST
            )
        }

        startRecording()
        startWorkoutTimer()

        stopButton.setOnClickListener {
            stopRecording()
        }
    }

    private fun startRecording() {
        try {
            camera = Camera.open()
            mediaRecorder = MediaRecorder()
            mediaRecorder.setCamera(camera)
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)

            val videoFile = File(getExternalFilesDir(null), "workout_${System.currentTimeMillis()}.mp4")
            mediaRecorder.setOutputFile(videoFile.absolutePath)
            mediaRecorder.setPreviewDisplay(surfaceView.holder.surface)
            mediaRecorder.prepare()
            mediaRecorder.start()
            isRecording = true
            userActivity = false
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error starting recording", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWorkoutTimer() {
        object : CountDownTimer(workoutDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                timerView.text = String.format("%02d:%02d", minutes, seconds)

                // Check for motion/activity
                if (motionDetector.detectMotion()) {
                    userActivity = true
                }
            }

            override fun onFinish() {
                timerView.text = "00:00"
                completeWorkout()
            }
        }.start()
    }

    private fun parseWorkoutDuration(workoutName: String): Long {
        return when {
            workoutName.contains("30") -> 30L
            workoutName.contains("25") -> 25L
            workoutName.contains("20") -> 20L
            workoutName.contains("15") -> 15L
            else -> 30L
        }
    }

    private fun completeWorkout() {
        stopRecording()
        if (userActivity) {
            // Workout was completed successfully
            val sharedPref = getSharedPreferences("WorkoutApp", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("last_workout_date", getCurrentDate())
            editor.apply()

            Toast.makeText(this, "Great work! Phone unlocked.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            // User just sat there
            Toast.makeText(
                this,
                "You need to actually workout! Phone stays locked.",
                Toast.LENGTH_SHORT
            ).show()
            // Restart the workout selection
            startActivity(Intent(this, WorkoutSelectionActivity::class.java))
            finish()
        }
    }

    private fun stopRecording() {
        try {
            if (isRecording) {
                mediaRecorder.stop()
                mediaRecorder.release()
                isRecording = false
            }
            camera?.release()
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }

    private fun getCurrentDate(): String {
        val cal = java.util.Calendar.getInstance()
        return "${cal.get(java.util.Calendar.YEAR)}-${cal.get(java.util.Calendar.MONTH)}-${cal.get(java.util.Calendar.DAY_OF_MONTH)}"
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRecording()
    }
}
