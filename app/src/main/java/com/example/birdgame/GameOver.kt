package com.example.birdgame

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class GameOver : AppCompatActivity() {
    private var startGameAgain: Button? = null
    private var displayScore: TextView? = null
    private var currentScore: Int = 0
    private val HIGH_SCORE_KEY = "highestScore"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        // Get current score from intent
        currentScore = intent.getIntExtra("Score", 0)

        // Load high score from SharedPreferences
        val sharedPref = getSharedPreferences("highScore", MODE_PRIVATE)
        var highScore = sharedPref.getInt(HIGH_SCORE_KEY, 0)

        // Update high score if current score is higher
        if (currentScore > highScore) {
            highScore = currentScore
            val editor = sharedPref.edit()
            editor.putInt(HIGH_SCORE_KEY, highScore)
            editor.apply() // Used apply() for asynchronous saving
            showNewBestScoreDialog(highScore)
        }

        // Update UI elements
        startGameAgain = findViewById(R.id.play_again_btn)
        displayScore = findViewById(R.id.displayScore)

        val displayText = "Score: $currentScore\nHighest Score: $highScore"
        displayScore!!.text = displayText

        startGameAgain!!.setOnClickListener {
            val mainIntent = Intent(this@GameOver, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }

    private fun showNewBestScoreDialog(newBestScore: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Best Score!")
        builder.setMessage("Congratulations! You've achieved a new best score: $newBestScore")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setOnDismissListener(DialogInterface.OnDismissListener { dialog -> dialog.dismiss() })
        builder.show()
    }
}
