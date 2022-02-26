package com.example.smartfarming.ui.gardens.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Reviews
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.data.room.entities.Garden
import com.example.smartfarming.ui.addactivities.ui.theme.*
import com.example.smartfarming.ui.adduser.ui.theme.BlueWatering
import com.example.smartfarming.ui.adduser.ui.theme.RedFertilizer
import com.example.smartfarming.ui.adduser.ui.theme.YellowPesticide
import com.example.smartfarming.ui.gardenprofile.composables.ToDos

@Composable
fun GardenProfile(garden : State<Garden?>){


    Scaffold(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(1f),
        floatingActionButton = {

        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GardenTitle(gardenName = garden.value!!.name)
            Report()

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                MainIcons(Icons.Default.Thermostat, "آب و هوا", waterBlueDark){

                }
                MainIcons(Icons.Outlined.Reviews, "توصیه ها", YellowPesticide){

                }
                MainIcons(Icons.Outlined.LocationOn, "مکان نما", RedFertilizer){

                }

            }


            ToDos("irrigation", "آبیاری فروردین", "به توجه به تقویم آبیاری و نیاز باغ، آبیاری انجام شود.")
            ToDos("pesticide", "سم پاشی", "در فروردین ماه به منظور جلوگیری از طغیان شته سم پاشی انجام شود.")
        }
    }
}



@Composable
fun Report(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
        .graphicsLayer {
            shadowElevation = 4.dp.toPx()
            shape = RoundedCornerShape(20.dp)
            clip = true
        }
        .background(Color.White)
        .clip(MaterialTheme.shapes.large)
        .clickable { }
        .padding(20.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(id = R.drawable.bar_chart),
            contentDescription = "",
            tint = MainGreen,
            modifier = Modifier
                .padding(8.dp)
                .size(55.dp)
        )
        Text(
            text = "گزارش فعالیت ها",
            style = MaterialTheme.typography.body2,
            color = MainGreen
        )

    }
}





@Composable
fun MainIcons(icon : ImageVector, text : String, color: Color , clickHandler : () -> Unit){

    Column(
        modifier = Modifier.padding(13.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            icon,
            contentDescription = "",
            modifier = Modifier.size(40.dp),
            tint = color
        )

        Text(text = text, style = MaterialTheme.typography.subtitle1, color = BorderGray)
    }
}

@Composable
fun GardenTitle(gardenName : String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.Edit, contentDescription = "", tint = MainGreen, modifier = Modifier
            .clickable { }
            .padding(5.dp))
        Text(text = gardenName, style = MaterialTheme.typography.h3, color = MainGreen, modifier = Modifier.padding(5.dp))

    }
}

@Composable
fun Indicators(){

}

