package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        // getting intent from last activity
        val pIntent = this.intent

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
        val image = pIntent.extras?.getInt("EXTRA_IMAGE")
        if (image != null) {
            qImg.setImageResource(image)
        }
        val score = pIntent.extras?.getInt("EXTRA_SCORE")

        val allQuestions: Array<Array<String>> = pIntent.extras?.getSerializable("EXTRA_QUESTIONS") as Array<Array<String>>
        val question: Array<String> = allQuestions[current!!]
        qText.text = question[0]
        choice1.text = question[1]
        choice2.text = question[2]
        choice3.text = question[3]
        choice4.text = question[4]

        // getting additional data to pass to intent
        val answer = question[5]

        submitBtn.setOnClickListener{
            val userChoice: RadioButton = findViewById(choices.checkedRadioButtonId)
            val newScore =
                score?.let { num -> calculateScore(num, userChoice.text.toString(), answer) }

            val intent = Intent(this, AnswerActivity::class.java)
            // adding extras to intent
            intent.putExtra("EXTRA_NUM_QUESTIONS", allQuestions.size)
            intent.putExtra("EXTRA_QUESTIONS", allQuestions)
            intent.putExtra("EXTRA_CURRENT", current)
            intent.putExtra("EXTRA_IMAGE", image)
            intent.putExtra("EXTRA_SCORE", newScore)
            intent.putExtra("EXTRA_CHOICE", userChoice.text)
            intent.putExtra("EXTRA_ANSWER", answer)

            Log.i("QuestionActivity", intent.extras.toString())
            startActivity(intent)
        }
    }

    private fun calculateScore(score: Int, choice: String, answer: String) : Int {
        if (score != null && choice == answer) {
            return score + 1
        }
        return score
    }
}