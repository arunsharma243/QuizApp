package com.example.quizapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.quizapp.R
import com.example.quizapp.presentation.util.Dimens
import com.example.quizapp.presentation.util.Dimens.LargeCornerRadius
import com.example.quizapp.presentation.util.Dimens.MediumTextSize
import com.example.quizapp.presentation.util.Dimens.SmallPadding

@Composable
fun ButtonBox(
    text:String,
    padding:Dp=SmallPadding,
    borderColor: Color = colorResource(id = R.color.blue_grey),
    containerColor:Color= colorResource(id = R.color.blue_grey),
    textColor:Color= colorResource(id = R.color.black),
    fontSize:TextUnit= MediumTextSize,
    fraction:Float=1f,
    onClick:()->Unit
){
    Box(
       modifier= Modifier
           .padding(padding)
           .border(4.dp,borderColor, RoundedCornerShape(LargeCornerRadius))
           .clickable { onClick() }
           .fillMaxWidth(fraction)
           .height(Dimens.MediumBoxHeight)
           .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
           .background(containerColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text=text,
            fontSize = Dimens.MediumTextSize,
            style=MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color=textColor
        )
    }
}