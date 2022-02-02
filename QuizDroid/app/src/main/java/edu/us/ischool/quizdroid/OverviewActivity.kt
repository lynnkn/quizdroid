package edu.us.ischool.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class OverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        val intent = this.intent
        val overviewText = findViewById<TextView>(R.id.overviewText)
        val overviewImg = findViewById<ImageView>(R.id.overviewImg)

        // getting the value from intent
        overviewText.text = intent.extras?.getString("EXTRA_OVERVIEW")
        val image = intent.extras?.getInt("EXTRA_IMAGE")
        if (image != null) {
            overviewImg.setImageResource(image)
        };
    }
}