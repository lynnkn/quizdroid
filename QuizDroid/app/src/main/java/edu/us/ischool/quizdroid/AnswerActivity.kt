package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        // getting intent from last activity
        val pIntent = this.intent

        // getting data
        val data = QuizApp.quizData
        val index = pIntent.extras?.getInt("EXTRA_INDEX")
        val topic = index?.let { data.getTopic(it) }

        // getting views from layout
        val aHeader: TextView = findViewById<TextView>(R.id.aHeader)
        val aText: TextView = findViewById<TextView>(R.id.aText)
        val aImg: ImageView = findViewById<ImageView>(R.id.aImg)
        val choice: TextView = findViewById<TextView>(R.id.yourAns)
        val answer: TextView = findViewById<TextView>(R.id.correctAns)
        val scoreText: TextView = findViewById<TextView>(R.id.score)
        val nextBtn: Button = findViewById<Button>(R.id.nextBtn)

        // setting view values (with null checks included)
        val current = pIntent.extras?.getInt("EXTRA_CURRENT")
        if (current != null) {
            aHeader.text = "Question ${current + 1}"
        }
        aImg.setImageResource(topic!!.img)
        val question: Quiz = topic!!.questionList[current!!]
        aText.text = question.question

        // setting score logic
        choice.text = "Your Answer: ${pIntent.extras?.getString("EXTRA_CHOICE")}"
        answer.text = "Correct Answer: ${question.choices[question.answer]}"
        var score = pIntent.extras?.getInt("EXTRA_SCORE")
        scoreText.text = "You got ${score} out of ${topic.questionList.size} correct!"

        // button logic
        if (current != null) {
            if (current + 1 === topic.questionList.size) {
                nextBtn.text = "FINISH"
                nextBtn.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                nextBtn.text = "NEXT"
                nextBtn.setOnClickListener {

                    val intent = Intent(this, QuestionActivity::class.java)

                    // adding extras to intent
                    intent.putExtra("EXTRA_CURRENT", current + 1)
                    intent.putExtra("EXTRA_INDEX", index)
                    intent.putExtra("EXTRA_SCORE", score)

                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}