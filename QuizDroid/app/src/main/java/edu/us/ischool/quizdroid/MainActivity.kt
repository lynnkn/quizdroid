package edu.us.ischool.quizdroid

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.os.Handler

import android.net.ConnectivityManager
import android.os.Environment
import android.os.Looper
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.lang.Exception
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    // field variables
    private lateinit var topicList: RecyclerView

    class IntentListener : BroadcastReceiver() {
        init {
            Log.i("IntentListener", "Intent listener created")
        }

        override fun onReceive(context: Context?, intent: Intent?) {
            val url = intent?.extras?.getString("EXTRA_URL")
            Toast.makeText(context?.applicationContext, "Attempting to download from ${url}", Toast.LENGTH_SHORT).show()

            // attempt to download
            if (url != null) {
                beginDownload(context, url)
            }
        }

        @Throws(FileNotFoundException::class)
        fun beginDownload(context: Context?, url: String) {
            // request to download at url
            val t = thread {
                val server = URL(url)
                val client: HttpURLConnection = server.openConnection() as HttpURLConnection
                client.requestMethod = "GET"

                try {
                    val reader = BufferedReader(InputStreamReader(client.inputStream))
                    val file = File(Environment.getExternalStorageDirectory().path, "questions.json")
                    val writer = file.bufferedWriter(Charsets.UTF_8, DEFAULT_BUFFER_SIZE)

                    var inputLine: String?

                    while (reader.readLine().also { inputLine = it } != null) {
                        writer.write(inputLine)
                        writer.newLine()
                    }

                    reader.close()
                    writer.close()

                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context?.applicationContext, "Download Successful!", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: FileNotFoundException) {
                    Log.i("IntentListener", "Download failed")
                    Handler(Looper.getMainLooper()).post {
                        val alert = AlertDialog.Builder(context)
                            .setTitle("Download Encountered Error")
                            .setMessage("The download failed. Would you like to retry?")
                            .setPositiveButton("YES") {dialog, id ->
                                beginDownload(context, url)
                            }
                            .setNegativeButton("NO") {dialog, id ->
                                // do nothing
                            }
                            .setIcon(android.R.drawable.ic_dialog_alert)

                        alert.show()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // manage preferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonURL = prefs.getString("url", R.string.json_url.toString())
        val download = prefs.getString("download", "5 min")?.dropLast(4)!!.toInt()

        // toolbar code
        val actionBar: Toolbar = findViewById(R.id.action_bar)
        setSupportActionBar(actionBar)
        val actionImg: ImageView = findViewById(R.id.preferences_img)
        actionImg.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // check if app is not connected to the internet
        if (isAirplaneModeOn(this)) {
            Toast.makeText(this, "Please turn off airplane mode to continue", Toast.LENGTH_SHORT)
                .show()
            Handler().postDelayed(Runnable {
                val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
                startActivity(intent)
            }, 3000)
            startActivity(intent)
        } else if (!isOnline()) {
            Toast.makeText(this, "Please make sure your device is connected ot the internet", Toast.LENGTH_LONG).show()
        } else {
            // setting up receiver
            val receiver = IntentListener()
            val intFilter = IntentFilter()
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
            // listening for intents
            intFilter.addAction("edu.us.ischool.QUIZDATA")
            registerReceiver(receiver, intFilter)
            val intent = Intent("edu.us.ischool.QUIZDATA")
            intent.putExtra("EXTRA_URL", jsonURL)
            Toast.makeText(this, jsonURL, Toast.LENGTH_SHORT).show()
            var pendingIntent : PendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager?.setRepeating(
                AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (download * 60000).toLong(), pendingIntent
            )
        }

        // bind adapter to the RecyclerView class
        topicList = findViewById(R.id.topicList)
        val adapter = TopicAdapter(this)
        topicList.adapter = adapter
        topicList.layoutManager = LinearLayoutManager(this)
    }

    private fun isAirplaneModeOn(context : Context) : Boolean {
        return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    private fun isOnline() : Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
