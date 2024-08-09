package com.example.quizapp.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.quizapp.presentation.common.AppDropDownMenu
import com.example.quizapp.presentation.common.ButtonBox
import com.example.quizapp.presentation.util.Dimens.MediumSpacerHeight
import com.example.quizapp.presentation.home.component.HomeHeader
import com.example.quizapp.presentation.nav_graph.Routes
import com.example.quizapp.presentation.util.Constants
import com.example.quizapp.presentation.util.Dimens.MediumPadding

@Composable
fun HomeScreen(
    state:HomeScreenState,
    event:(HomeScreenEvent)-> Unit,
    navController: NavController
){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ){
          HomeHeader()

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(menuName = "Number of Questions:", menuList =Constants.numberAsString , text=state.numberOfQuiz.toString(),onDropDownClick = {event(HomeScreenEvent.SetNumberOfQuizzes(it.toInt()))})

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(menuName = "Select Category:", menuList =Constants.categories,text=state.category,onDropDownClick = {event(HomeScreenEvent.SetQuizCategory(it))})

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(menuName = "Select Difficulty:", menuList =Constants.difficulty ,text=state.difficulty,onDropDownClick = {event(HomeScreenEvent.SetQuizDifficulty(it))})

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(menuName = "Select Type:", menuList =Constants.type,text=state.type,onDropDownClick = {event(HomeScreenEvent.SetQuizType(it))} )

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        ButtonBox(text = "Generate Quiz", padding = MediumPadding){
//            Log.d("quiz detail","${state.numberOfQuiz} ${state.category} ${state.difficulty} ${state.type}")

            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(state.numberOfQuiz,state.category,state.difficulty,state.type)
            )
        }

        }
}


