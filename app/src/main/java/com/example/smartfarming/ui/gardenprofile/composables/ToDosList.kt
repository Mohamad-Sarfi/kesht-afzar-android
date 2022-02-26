package com.example.smartfarming.ui.gardenprofile.composables

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.smartfarming.ui.addactivities.ui.theme.BorderGray
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.adduser.ui.theme.BlueWatering
import com.example.smartfarming.ui.adduser.ui.theme.RedFertilizer
import com.example.smartfarming.ui.adduser.ui.theme.YellowPesticide

@Composable
fun ToDos(activityType: String, title : String, data: String){

    val barColor = when(activityType){
        "irrigation" -> BlueWatering
        "fertilizarion" -> RedFertilizer
        "pesticide" -> YellowPesticide
        else -> MainGreen
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val cardHeight by animateDpAsState(
        if (expanded) 135.dp else 90.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .graphicsLayer {
                shadowElevation = 4.dp.toPx()
                shape = RoundedCornerShape(15.dp)
                clip = true
            }
            .background(Color.White)
            .clip(MaterialTheme.shapes.large)
            .clickable {
                expanded = !expanded
            }
            .padding(15.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(4.dp)
            .background(barColor)
            .padding(vertical = 2.dp, horizontal = 5.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.body2, color = BorderGray)
            DetailsText(expanded, data)
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DetailsText(expanded : Boolean, data: String){
    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn(
            // customize with tween AnimationSpec
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 100,
                easing = LinearOutSlowInEasing
            )
        ),
        // you can also add animationSpec in fadeOut if need be.
        exit = fadeOut() + shrinkHorizontally(),

        ) {
        Text(text = data,
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.subtitle1,
            color = BorderGray
        )
    }
}
