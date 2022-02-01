package edu.us.ischool.quizdroid

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// importing data from another class
var questionData = Data()

class RowHolder(row: View) : RecyclerView.ViewHolder(row) {
    var topicLabel: TextView? = null

    init {
        topicLabel = row.findViewById<Button>(R.id.topicLabel)
    }

    fun bindModel(item: String) {
        // playerLabel is nullable so have to put !!
        topicLabel!!.text = item
    }
}

class TopicAdapter(val activity: Activity) : RecyclerView.Adapter<RowHolder>() {
    private val topicLabels = questionData.getTopicLabels()

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
        holder.bindModel(topicLabels[position])

        // setting button listeners
        holder.topicLabel?.setOnClickListener{

        }
    }
}
