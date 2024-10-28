package com.example.birdgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class splashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the splash activity
        setContentView(R.layout.activity_splash)

        // Create a new thread to handle the splash screen timing
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    // Pause the thread for 5000 milliseconds (5 seconds)
                    sleep(5000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    // After the specified time, start the MainActivity
                    val mainIntent = Intent(this@splashActivity, MainActivity::class.java)
                    startActivity(mainIntent)
                }
            }
        }
        // Start the thread
        thread.start()
    }

    override fun onPause() {
        super.onPause()
        // When the splash activity is paused, finish it to prevent it from being returned to
        finish()
    }
}
