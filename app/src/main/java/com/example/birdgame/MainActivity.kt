package com.example.birdgame

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private var gameView: FlyingFishView? = null // Declare a variable to hold the game view

    // Create a handler to manage UI updates
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the game view
        gameView = FlyingFishView(this)

        // Set the content view to the game view
        setContentView(gameView)

        // Create a timer to schedule periodic updates of the game view
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                // Use the handler to post an invalidate message to the game view
                handler.post { gameView!!.invalidate() }
            }
        }, 0, Interval) // Schedule the task to run periodically with the specified interval
    }

    companion object {
        private const val Interval: Long = 30 // Define the interval between updates in milliseconds
    }
}
