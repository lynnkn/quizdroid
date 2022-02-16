package edu.us.ischool.quizdroid

import android.os.Environment
import org.json.JSONArray
import java.io.File

// JSON Data: https://gist.github.com/lynnkn/428fd93053d820e818982a2be76458ec

class QuizData : TopicRepository {
    private val topicList: MutableList<Topic> = mutableListOf()

    init {
        fetchJSONData()
    }

    private fun fetchJSONData() {
        val file = File("/sdcard/" + Environment.getExternalStorageDirectory().path, "questions.json")
        val fileText = file.inputStream().readBytes().toString(Charsets.UTF_8)

        val data = JSONArray(fileText)
        for (i in 0..(data.length() - 1)){
            val topicObj = data.getJSONObject(i)
            val questionListObj = topicObj.getJSONArray("questions")
            val questionList = arrayListOf<Quiz>()
            for (j in 0..(questionListObj.length() - 1)) {
                val quizObj = questionListObj.getJSONObject(j)
                val choicesObj = quizObj.getJSONArray("answers")
                val choices = arrayListOf<String>()
                for (k in 0..3) {
                    choices.add(choicesObj[k].toString())
                }
                questionList.add(Quiz(quizObj.getString("text"), choices, (quizObj.getInt("answer") - 1)))
            }
            topicList.add(Topic(
                topicObj.getString("title"),
                topicObj.getString("desc"),
                questionList
            ))
        }

    }

    // getter methods
    override fun getAllTopics() : List<Topic> {
        return topicList
    }

    override fun getTopic(index: Int) : Topic {
        return topicList[index]
    }

    override fun addTopic(topic: Topic) {
        topicList.add(topic)
    }

    override fun updateTopic(index: Int, update: Topic) {
        topicList[index] = update
    }

    override fun removeTopic(index: Int) {
        topicList.removeAt(index)
    }
}

