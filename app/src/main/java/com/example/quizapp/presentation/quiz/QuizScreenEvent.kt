package com.example.quizapp.presentation.quiz

sealed class QuizScreenEvent {
    data class GetQuizzes(
        val numOfQuizzes: Int,
        val category: Int,
        val difficulty: String,
        val type: String
    ) : QuizScreenEvent()

    data class SetOptionSelected(
        val quizStateIndex: Int,
        val selectedOption: Int
    ):QuizScreenEvent()
}