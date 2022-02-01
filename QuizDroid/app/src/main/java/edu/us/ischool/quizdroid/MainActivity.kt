package edu.us.ischool.quizdroid

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // field variables
    private lateinit var topicList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bind adapter to the RecyclerView class
        topicList = findViewById<RecyclerView>(R.id.topicList)
        val adapter = TopicAdapter(this)
        topicList.adapter = adapter
        topicList.layoutManager = LinearLayoutManager(this);
    }
}
