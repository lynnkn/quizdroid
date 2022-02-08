package edu.us.ischool.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "QuizApp is loaded and running!")
    }

    companion object {
        val quizData = QuizData()
    }
}