package edu.us.ischool.quizdroid

interface TopicRepository {
    fun getTopics() : List<Topic>
    fun addTopic(topic: Topic)
}

data class Quiz(val question: String, val choice1: String, val choice2: String, val choice3: String,
                val choice4: String, val answer: Int)

data class Topic(val title: String, var short: String, var long: String,
                 var questionList: List<Quiz>)
