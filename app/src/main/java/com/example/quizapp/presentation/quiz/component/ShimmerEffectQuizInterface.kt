package com.example.quizapp.presentation.quiz.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.presentation.util.Dimens.LargeCornerRadius
import com.example.quizapp.presentation.util.Dimens.SmallPadding


@Composable
fun ShimmerEffectQuizInterface() {
    Column{
        Row(
            modifier=Modifier.padding(SmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier=Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier=Modifier.width(5.dp))
            Box(
                modifier=Modifier
                    .weight(9f)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
        }
        Spacer(modifier=Modifier.height(50.dp))
        Column(
            modifier=Modifier
                .padding(horizontal=15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(LargeCornerRadius))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(LargeCornerRadius))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(LargeCornerRadius))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(LargeCornerRadius))
                    .shimmerEffect()
            )


            Spacer(modifier = Modifier.height(80.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(LargeCornerRadius))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(LargeCornerRadius))
                        .shimmerEffect()
                )
            }
        }
    }
}

@Preview
@Composable
fun aa() {
    ShimmerEffectQuizInterface()
}
fun Modifier.shimmerEffect()= composed {
    val transition = rememberInfiniteTransition(label = " ")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable<Float>(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value
    background(color = colorResource(id = R.color.blue_grey).copy(alpha = alpha))
}