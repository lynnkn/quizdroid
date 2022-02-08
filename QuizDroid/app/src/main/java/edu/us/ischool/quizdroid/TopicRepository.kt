package edu.us.ischool.quizdroid

import java.io.Serializable

interface TopicRepository {
    fun getAllTopics() : List<Topic>
    fun getTopic(index: Int) : Topic
    fun addTopic(topic: Topic)
    fun updateTopic(index: Int, newTopic: Topic)
    fun removeTopic(index: Int)
}

data class Quiz(val question: String, val choices: List<String>, val answer: Int) : Serializable

data class Topic(val title: String, var desc: String, var img: Int,
                 var questionList: List<Quiz>) : Serializable
