package com.example.quizapp.presentation.score

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.presentation.nav_graph.Routes
import com.example.quizapp.presentation.util.Dimens
import com.example.quizapp.presentation.util.Dimens.LargeTextSize
import com.example.quizapp.presentation.util.Dimens.MediumCornerRadius
import com.example.quizapp.presentation.util.Dimens.MediumPadding
import com.example.quizapp.presentation.util.Dimens.MediumTextSize
import com.example.quizapp.presentation.util.Dimens.SmallTextSize
import java.text.DecimalFormat


@Composable
fun ScoreScreen(
    numOfQuestions:Int,
    numOfCorrectAns:Int,
    navController: NavController
) {
    BackHandler {
        goToHome(navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal=MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
               onClick = {
                   goToHome(navController=navController)
                         },

                ) {
                Icon(
                    painterResource(id = R.drawable.close),
                    contentDescription = "Close",
                    tint = colorResource(id = R.color.blue_grey)
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(MediumCornerRadius))
                .background(colorResource(id = R.color.blue_grey))
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimens.MediumPadding,
                    vertical = Dimens.MediumPadding
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("You attempted")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("$numOfQuestions questions")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" and from that ")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.green))) {
                        append("$numOfCorrectAns answer")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" are correct")
                    }
                }
                val scorePercentage = calculatePercentage(numOfQuestions, numOfCorrectAns)
//                LottieAnimation(modifier=Modifier.height((SmallSpacerHeight)),composition = , progress = { /*TODO*/ })

                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = MediumTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.green),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = LargeTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                Text(
                    text = "Quiz completed successfully",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = SmallTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                Text(
                    text = annotatedString,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = SmallTextSize,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Share with us : ",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = SmallTextSize,
                    )
                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.insta),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = ""
                    )

                }

            }

        }
    }
}

fun goToHome(navController: NavController){
    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){inclusive=true}
    }
}

fun calculatePercentage(numOfQuestions: Int, numOfCorrectAns: Int): Double {
require(numOfCorrectAns>=0 && numOfQuestions>0){"Invalid input: number of correct answers must be non-negative and number of questions must be a positive"}
    val percentage=(numOfCorrectAns.toDouble()/numOfQuestions.toDouble())*100.0
return DecimalFormat("#.##").format(percentage).toDouble()
}

//@Preview()
//@Composable
//fun ScoreScreenPreview() {
//    ScoreScreen(numOfQuestions = 10, numOfCorrectAns = 2)
//}