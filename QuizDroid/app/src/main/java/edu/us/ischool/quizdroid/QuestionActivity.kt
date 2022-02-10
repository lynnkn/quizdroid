package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.Toolbar
import java.io.Serializable

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        // toolbar code
        val actionBar: Toolbar = findViewById(R.id.action_bar)
        setSupportActionBar(actionBar)
        val actionImg: ImageView = findViewById(R.id.preferences_img)
        actionImg.setOnClickListener {
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
        }

        // getting intent from last activity
        val pIntent = this.intent

        // getting data
        val data = QuizApp.quizData
        val index = pIntent.extras?.getInt("EXTRA_INDEX")
        val topic = index?.let { data.getTopic(it) }

        // getting views from layout
        val qHeader: TextView = findViewById(R.id.qHeader)
        val qText: TextView = findViewById(R.id.qText)
        val qImg: ImageView = findViewById(R.id.qImg)
        val choices: RadioGroup = findViewById(R.id.choices)
        val choice1: RadioButton = findViewById(R.id.choice1)
        val choice2: RadioButton = findViewById(R.id.choice2)
        val choice3: RadioButton = findViewById(R.id.choice3)
        val choice4: RadioButton = findViewById(R.id.choice4)
        val submitBtn: Button = findViewById(R.id.submitBtn)

        // setting view values (with null checks included)
        val current = pIntent.extras?.getInt("EXTRA_CURRENT")
        if (current != null) {
            qHeader.text = "Question ${current + 1}"
        }
        val score = pIntent.extras?.getInt("EXTRA_SCORE")
        qImg.setImageResource(R.drawable.default_img)

        val question: Quiz = topic!!.questionList[current!!]
        qText.text = question.question
        choice1.text = question.choices[0]
        choice2.text = question.choices[1]
        choice3.text = question.choices[2]
        choice4.text = question.choices[3]

        submitBtn.setOnClickListener{
            if (choices.checkedRadioButtonId != -1) {
                val userChoice: RadioButton = findViewById(choices.checkedRadioButtonId)
                val newScore =
                    score?.let { num -> calculateScore(num, userChoice.text.toString(), question.choices[question.answer]) }

                val intent = Intent(this, AnswerActivity::class.java)
                // adding extras to intent
                intent.putExtra("EXTRA_CURRENT", current)
                intent.putExtra("EXTRA_INDEX", index)
                intent.putExtra("EXTRA_SCORE", newScore)
                intent.putExtra("EXTRA_CHOICE", userChoice.text)

                startActivity(intent)
            }
        }
    }

    private fun calculateScore(score: Int, choice: String, answer: String) : Int {
        if (score != null && choice == answer) {
            return score + 1
        }
        return score
    }
}