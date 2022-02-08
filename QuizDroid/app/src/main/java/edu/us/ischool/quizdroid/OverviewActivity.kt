package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import java.io.Serializable

class OverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        // grabbing data from repo
        val data = QuizApp.quizData

        // grabbing views from layout
        val overviewText: TextView = findViewById(R.id.overviewText)
        val overviewImg: ImageView = findViewById(R.id.overviewImg)
        val overviewQuestions: TextView = findViewById(R.id.overviewQuestions)
        val startBtn: Button = findViewById(R.id.startBtn)

        // getting the value from intent and setting up screen
        val pIntent = this.intent
        val index = pIntent.extras?.getInt("EXTRA_INDEX")
        val topic = index?.let { data.getTopic(it) }

        overviewText.text = topic!!.desc
        overviewQuestions.text = "There will be a total of ${topic!!.questionList.size} questions in this topic."
        overviewImg.setImageResource(topic!!.img)

        startBtn.setOnClickListener{
            val intent = Intent(this, QuestionActivity::class.java)

            // adding extras to intent
            intent.putExtra("EXTRA_CURRENT", 0)
            intent.putExtra("EXTRA_INDEX", index)
            intent.putExtra("EXTRA_SCORE", 0)

            startActivity(intent)
            finish()
        }
    }
}