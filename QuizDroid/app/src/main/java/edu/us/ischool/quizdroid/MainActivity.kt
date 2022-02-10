package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceActivity
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // field variables
    private lateinit var topicList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // toolbar code
        val actionBar: Toolbar = findViewById(R.id.action_bar)
        setSupportActionBar(actionBar)
        val actionImg: ImageView = findViewById(R.id.preferences_img)
        actionImg.setOnClickListener {
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
        }

        // bind adapter to the RecyclerView class
        topicList = findViewById(R.id.topicList)
        val adapter = TopicAdapter(this)
        topicList.adapter = adapter
        topicList.layoutManager = LinearLayoutManager(this)
    }
}
