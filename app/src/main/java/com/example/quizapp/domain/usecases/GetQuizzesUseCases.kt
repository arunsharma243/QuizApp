package com.example.quizapp.domain.usecases

import com.example.quizapp.common.Resource
import com.example.quizapp.domain.model.Quiz
import com.example.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuizzesUseCases (
    val quizRepository: QuizRepository
){

    operator fun invoke(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Flow<Resource<List<Quiz>>> = flow{

        emit(Resource.Loading())

        try {
            emit(Resource.Success(data=quizRepository.getQuizzes(amount,category,difficulty,type)))
        }
        catch (e:Exception){
            emit(Resource.Error(message = e.message.toString()))
        }
           }.flowOn(Dispatchers.IO)

}