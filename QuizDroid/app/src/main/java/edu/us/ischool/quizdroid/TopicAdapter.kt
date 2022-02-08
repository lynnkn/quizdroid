package edu.us.ischool.quizdroid

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

// importing data from another class

class RowHolder(row: View) : RecyclerView.ViewHolder(row) {
    var topicLabel: TextView? = null

    init {
        topicLabel = row.findViewById<Button>(R.id.topicLabel)
    }

    fun bindModel(item: Topic) {
        // getting data values for topic
        topicLabel!!.text = item.title
    }
}

class TopicAdapter(val activity: Activity) : RecyclerView.Adapter<RowHolder>() {
    val quiz = QuizApp.quizData

    override fun getItemCount() : Int { return quiz.getAllTopics().size }

    // recycler view inside activity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RowHolder {
        return RowHolder(
            // displays the row
            activity.layoutInflater.inflate(R.layout.topic_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        // asks row holder to bind this data into its UI
        holder.bindModel(quiz.getAllTopics()[position])

        // setting listener
        holder.topicLabel?.setOnClickListener{
            val intent = Intent(activity, OverviewActivity::class.java)

            // adding extras to intent
            intent.putExtra("EXTRA_INDEX", position)

            Log.i("MainActivity", intent.extras.toString())
            activity.startActivity(intent)
        }
    }
}
