package com.example.quizapp.presentation.quiz

import com.example.quizapp.domain.model.Quiz

data class QuizScreenState(
    val isLoading: Boolean=false,
    val quizState: List<QuizState> = emptyList(),
    val error : String="",
    val score: Int=0
)

data class QuizState(
    val quiz:Quiz ?= null,
    val shuffledOptions: List<String> = emptyList(),
    val selectedOptions:Int ? = -1
)