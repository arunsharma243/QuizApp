package com.example.quizapp.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.common.Resource
import com.example.quizapp.domain.model.Quiz
import com.example.quizapp.domain.usecases.GetQuizzesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizzesUseCases:GetQuizzesUseCases
):ViewModel() {

    private val _quizList= MutableStateFlow(QuizScreenState())
    val quizList=_quizList

    fun onEvent(event:QuizScreenEvent){
        when(event){
            is QuizScreenEvent.GetQuizzes->{
                getQuizzes(event.numOfQuizzes,event.category,event.difficulty,event.type)

            }
            is QuizScreenEvent.SetOptionSelected->{
                updateQuizStateList(event.quizStateIndex,event.selectedOption)
            }
        }
    }

    private fun updateQuizStateList(quizStateIndex: Int, selectedOption: Int) {
        val updatedQuizStateList= mutableListOf<QuizState>()

        _quizList.value.quizState.forEachIndexed{index,quizState->
             updatedQuizStateList.add(
                 if(quizStateIndex==index){
                     quizState.copy(selectedOptions=selectedOption)
                 }
                 else quizState
             )
         }
        _quizList.value=quizList.value.copy(quizState = updatedQuizStateList)

        udptaeScore(_quizList.value.quizState[quizStateIndex])
    }

    private fun udptaeScore(quizState: QuizState) {
        if(quizState.selectedOptions!=-1)
        {
            val correctAnswer=quizState.quiz?.correct_answer
            val selectedAnswer=quizState.selectedOptions?.let{
                quizState.shuffledOptions[it].replace("&quot;","\"").replace("&#039;","\'")
            }
            Log.d("check","$correctAnswer->$selectedAnswer")
            if(correctAnswer==selectedAnswer){
                val previousScore=_quizList.value.score
                _quizList.value=quizList.value.copy(score = previousScore+1)
            }
        }

    }

    private fun getQuizzes(amount:Int ,category:Int, difficulty: String, type: String){
       viewModelScope.launch {
           getQuizzesUseCases(amount, category, difficulty, type).collect { resource ->
               when (resource) {
                   is Resource.Loading-> {
                       _quizList.value=QuizScreenState(isLoading = true)
                   }
                   is Resource.Success->{
                       val listOfQuizState:List<QuizState> = getListOfQuizState(resource.data)
                       _quizList.value=QuizScreenState(quizState=listOfQuizState)
                   }
                   is Resource.Error->{
                       _quizList.value= QuizScreenState(error=resource.message.toString())
                   }
               }

           }
       }



    }

    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {

        val listOfQuizState=mutableListOf<QuizState>()

        for(quiz in data!!)
        {

            val shuffledOptions= mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }

            listOfQuizState.add(QuizState(quiz,shuffledOptions,-1))
        }
   return listOfQuizState
    }
}