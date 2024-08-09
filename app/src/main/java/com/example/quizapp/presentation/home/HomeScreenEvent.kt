package com.example.quizapp.presentation.home

sealed class HomeScreenEvent {

    data class SetNumberOfQuizzes(val numberOfQuizzes: Int):HomeScreenEvent()
    data class SetQuizCategory(val category: String):HomeScreenEvent()
    data class SetQuizDifficulty(val difficulty: String):HomeScreenEvent()
    data class SetQuizType(val type: String):HomeScreenEvent()
}