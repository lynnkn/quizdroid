package edu.us.ischool.quizdroid

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

// importing data from another class
var questionData = Data()

class RowHolder(row: View) : RecyclerView.ViewHolder(row) {
    var topicLabel: TextView? = null
    var overview: String? = null
    var questions: Array<Array<String>>? = null
    var image: Int? = null

    init {
        topicLabel = row.findViewById<Button>(R.id.topicLabel)
    }

    fun bindModel(item: String, position: Int) {
        // getting data values for topic
        topicLabel!!.text = item
        overview = questionData.getOverview(position)
        questions = questionData.getQuestions(position)
        image = questionData.getImage(position)
    }
}

class TopicAdapter(val activity: Activity) : RecyclerView.Adapter<RowHolder>() {
    private val topicLabels = questionData.getLabels()

    override fun getItemCount() : Int { return topicLabels.size }

    // recycler view inside activity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RowHolder {
        return RowHolder(
            // displays the row
            activity.layoutInflater.inflate(R.layout.topic_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        // asks row holder to bind this data into its UI
        holder.bindModel(topicLabels[position], position)

        // setting listener
        holder.topicLabel?.setOnClickListener{
            val intent = Intent(activity, OverviewActivity::class.java)

            // adding extras to intent
            intent.putExtra("EXTRA_OVERVIEW", holder.overview)
            intent.putExtra("EXTRA_QUESTIONS", holder.questions)
            intent.putExtra("EXTRA_IMAGE", holder.image)

            activity.startActivity(intent)
        }
    }
}
