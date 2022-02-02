package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class OverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        // grabbing views from layout
        val overviewText: TextView = findViewById<TextView>(R.id.overviewText)
        val overviewImg: ImageView = findViewById<ImageView>(R.id.overviewImg)
        val startBtn: Button = findViewById<Button>(R.id.startBtn)

        // getting the value from intent and setting up screen
        val pIntent = this.intent
        overviewText.text = pIntent.extras?.getString("EXTRA_OVERVIEW")
        val image = pIntent.extras?.getInt("EXTRA_IMAGE")
        if (image != null) {
            overviewImg.setImageResource(image)
        }

        // getting additional values to pass onto question screen
        val questions: Array<Array<String>> = pIntent.extras?.getSerializable("EXTRA_QUESTIONS") as Array<Array<String>>

        startBtn.setOnClickListener{
            val intent = Intent(this, QuestionActivity::class.java)

            // adding extras to intent
            intent.putExtra("EXTRA_QUESTIONS", questions)
            intent.putExtra("EXTRA_CURRENT", 0)
            intent.putExtra("EXTRA_IMAGE", image)
            intent.putExtra("EXTRA_SCORE", 0)

           startActivity(intent)
        }
    }
}