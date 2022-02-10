package edu.us.ischool.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        // toolbar code
        val actionBar: Toolbar = findViewById(R.id.action_bar)
        setSupportActionBar(actionBar)

        val downloadSpinner = findViewById<Spinner>(R.id.download_select)
        // setting up spinner values
        val times = resources.getStringArray(R.array.pref_download_check)
        if (downloadSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, times
            )
            downloadSpinner.adapter = adapter
        }

        Log.i("PreferenceActivity", "activity started")
    }
}