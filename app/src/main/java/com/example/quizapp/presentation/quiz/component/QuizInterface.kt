package com.example.quizapp.presentation.quiz.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.presentation.quiz.QuizState
import com.example.quizapp.presentation.util.Dimens
import com.example.quizapp.presentation.util.Dimens.ExtraLargeSpacerHeight
import com.example.quizapp.presentation.util.Dimens.SmallSpacerHeight

@Composable
fun QuizInterface(
    onOptionSelected: (Int)->Unit,
    qNumber:Int,
    quizState: QuizState,
    modifier: Modifier =Modifier
){
    val question=quizState.quiz?.question!!.replace("&quot;","\"").replace("&#039;","\'")
    Box(
        modifier=modifier,
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier=Modifier.wrapContentHeight().verticalScroll(rememberScrollState())
        ){
            Row(
                modifier=Modifier.fillMaxWidth()
            ){
                Text(
                    modifier=Modifier.weight(1f),
                    text="$qNumber.",
                    color= colorResource(id = R.color.blue_grey),
                    fontSize = Dimens.SmallTextSize
                    )
                Text(
                    modifier=Modifier.weight(9f),
                    text=question,
                    color= colorResource(id = R.color.blue_grey),
                    fontSize = Dimens.SmallTextSize
                )
            }
            Spacer(modifier=Modifier.height(Dimens.LargeSpacerHeight))

            Column(
                modifier=Modifier.padding(horizontal = 15.dp)
            ){
                 val options= listOf(
                     "A" to quizState.shuffledOptions[0].replace("&quot;","\"").replace("&#039;","\'"),
                     "B" to quizState.shuffledOptions[1].replace("&quot;","\"").replace("&#039;","\'"),
                     "C" to quizState.shuffledOptions[2].replace("&quot;","\"").replace("&#039;","\'"),
                     "D" to quizState.shuffledOptions[3].replace("&quot;","\"").replace("&#039;","\'")
                 )

                Column{
                    options.forEachIndexed{ index,(optionNumber,optionText)->
                        if(optionText.isNotEmpty()){
                            QuizOption(
                                optionNumber=optionNumber,
                                options=optionText,
                                onOptionClick = {onOptionSelected(index)},
                                selected = quizState.selectedOptions==index,
                                onUnselectedOption = {onOptionSelected(-1)}
                            )
                        }
                        Spacer(modifier=Modifier.height(SmallSpacerHeight))
                    }
                }
                Spacer(modifier=Modifier.height(ExtraLargeSpacerHeight))
            }
        }
    }
}