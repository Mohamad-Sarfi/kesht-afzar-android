package com.example.smartfarming.ui.addactivities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.Screens.FertilizationPart
import com.example.smartfarming.ui.addactivities.Screens.IrrigationPart
import com.example.smartfarming.ui.addactivities.Screens.OtherActivityPart
import com.example.smartfarming.ui.addactivities.Screens.PesticidePart
import com.example.smartfarming.ui.addactivities.ui.theme.*

@Composable
fun HostActivity(
    gardenName : String,
    activityName: String = "",
    icon: Int = 0
){
    // States
    var currentActivity by remember {
        mutableStateOf(activityName)
    }


    Column(modifier = Modifier
        .fillMaxSize(3f)
        .fillMaxHeight(1f)
        .fillMaxWidth(1f)
        .padding(top = 50.dp, end = 0.dp, start = 0.dp, bottom = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.End)
            ,
        ) {
            Row(modifier = Modifier){
                ActivityTitleCard(currentActivity)
            }
        }
        Row{
            Column(modifier = Modifier
                .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                SideBar(
                    currentActivity
                ) { currentActivity = it }
            }

            Column(modifier = Modifier
                .fillMaxHeight(1f)
                ,
                verticalArrangement = Arrangement.Center
            ) {
                DecideActivityPart(currentActivity, gardenName)
            }
        }
    }
}


@Composable
fun ActivityTitleCard(
    currentActivity: String
){

    val icon = when(currentActivity){
        ActivitiesScreen.PesticideBody.name -> R.drawable.pesticide_colored
        ActivitiesScreen.FertilizationBody.name -> R.drawable.fertilizer_color
        ActivitiesScreen.IrrigationBody.name -> R.drawable.irrigation_colored
        ActivitiesScreen.OtherActivityBody.name -> R.drawable.shovel_colored
        else -> R.drawable.shovel_colored
    }

    val backgroundColor = when(currentActivity){
        ActivitiesScreen.PesticideBody.name -> lightGreenPesticide
        ActivitiesScreen.FertilizationBody.name -> lightRedFertilizer
        ActivitiesScreen.IrrigationBody.name -> lightBlueIrrigation
        ActivitiesScreen.OtherActivityBody.name -> lightBrownOtherActivities
        else -> lightBrownOtherActivities
    }


        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 40.dp,
                        bottomStart = 40.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(backgroundColor)
                .padding(start = 10.dp, top = 30.dp, end = 30.dp, bottom = (10).dp)
        ){
            Image(
                painter = painterResource(id = icon),
                contentDescription = "pesticide",
                modifier = Modifier
                    .size(150.dp)

            )

        }
}

@Composable
fun SideBar(
    currentActivity : String,
    currentActivityChange : (String) -> Unit
){
    Column(
        modifier = Modifier
            .width(95.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 40.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(Color(0xFFECECEC))
            .padding(vertical = 30.dp, horizontal = 10.dp)
    ) {

        val irrigationIconTint by animateColorAsState(
            if (currentActivity == ActivitiesScreen.IrrigationBody.name) MainGreen else Color.Black
        )
        val irrigationIconSize by animateDpAsState(
            if (currentActivity == ActivitiesScreen.IrrigationBody.name) 50.dp else 40.dp
        )
        val otherActivitiesIconSize by animateDpAsState(
            if (currentActivity == ActivitiesScreen.OtherActivityBody.name) 50.dp else 40.dp
        )
        val fertilizationIconSize by animateDpAsState(
            if (currentActivity == ActivitiesScreen.FertilizationBody.name) 50.dp else 40.dp
        )
        val pesticideIconSize by animateDpAsState(
            if (currentActivity == ActivitiesScreen.PesticideBody.name) 50.dp else 40.dp
        )

        Icon(
            painter = painterResource(id = R.drawable.irrigation_line1),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    currentActivityChange(ActivitiesScreen.IrrigationBody.name)
                }
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(irrigationIconSize)

            ,
            tint = irrigationIconTint
        )
        Icon(
            painter = painterResource(id = R.drawable.shove_line),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    currentActivityChange(ActivitiesScreen.OtherActivityBody.name)
                }
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(otherActivitiesIconSize)

            ,
            tint = if (currentActivity == ActivitiesScreen.OtherActivityBody.name) MainGreen else Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.fertilizer_line),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    currentActivityChange(ActivitiesScreen.FertilizationBody.name)
                }
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(fertilizationIconSize)

            ,
            tint = if (currentActivity == ActivitiesScreen.FertilizationBody.name) MainGreen else Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.pesticide_line),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    currentActivityChange(ActivitiesScreen.PesticideBody.name)
                }
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(pesticideIconSize)

            ,
            tint = if (currentActivity == ActivitiesScreen.PesticideBody.name) MainGreen else Color.Black
        )
    }
}



@Composable
fun GardenTitle(gardenName : String){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$gardenName",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .align(CenterVertically),
            color = MainGreen
        )
        Text(
            text = " ثبت تغذیه باغ ",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .align(CenterVertically),
            color = MainGreen
        )
    }
}

@Composable
fun DecideActivityPart(
    currentActivity : String,
    gardenName: String
){
    when(currentActivity){
        ActivitiesScreen.FertilizationBody.name -> FertilizationPart(gardenName)
        ActivitiesScreen.IrrigationBody.name -> IrrigationPart(gardenName)
        ActivitiesScreen.PesticideBody.name -> PesticidePart()
        ActivitiesScreen.OtherActivityBody.name -> OtherActivityPart()
    }
}


@Preview(showBackground = true)
@Composable
fun defaultPreview4(){
    SmartFarmingTheme {
        HostActivity("باغ اکبر", "", 0)
    }
}