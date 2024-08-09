package com.example.quizapp.data.repository

import android.util.Log
import com.example.quizapp.data.remote.QuizApi
import com.example.quizapp.domain.model.Quiz
import com.example.quizapp.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val quizApi: QuizApi
):QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        val resp=quizApi.getQuizzes(amount,category,difficulty,type).results

        Log.d("quiz",resp.toString())
       return resp
    }
}