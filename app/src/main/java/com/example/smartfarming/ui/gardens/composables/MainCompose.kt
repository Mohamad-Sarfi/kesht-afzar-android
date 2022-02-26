package com.example.smartfarming.ui.gardens.composables

import android.content.Intent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.data.room.entities.Garden
import com.example.smartfarming.ui.addactivities.AddActivities
import com.example.smartfarming.ui.addactivities.ui.theme.*
import com.example.smartfarming.ui.addgarden.AddGarden
import com.example.smartfarming.ui.gardens.GardensViewModel
import com.example.smartfarming.ui.home.composables.MyFAB


@Composable
fun GardenCompose(viewModel: GardensViewModel){

    val gardenList by viewModel.getGardens().observeAsState()
    val contex = LocalContext.current

    var fabExtended by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
        },
        floatingActionButton = {
            MyFAB(context = contex, fabExtended = fabExtended) {
                fabExtended =! fabExtended
            }
        }
    ) {
        Column() {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(
                    Icons.Default.Add,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            val intent = Intent(contex, AddGarden::class.java)
                            contex.startActivity(intent)
                        }
                        .size(40.dp)
                    ,
                    tint = MainGreen
                )

                Text(
                    text = "باغ های شما",
                    style = MaterialTheme.typography.h3,
                    color = MainGreen
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .weight(1f, false)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
                if (gardenList.isNullOrEmpty()){
                    Text(
                        text = "هنوز باغی وارد نشده!",
                        style = MaterialTheme.typography.body1,
                        color = MainGreen
                    )
                } else {
                    LazyColumn{
                        items(gardenList!!){
                            GardenCard(garden = it)
                        }
                    }
                }
        }
    }
}




@Composable
fun ActivitySticker(job : String){

    val activities = stringArrayResource(id = R.array.garden_activities)

    Box(modifier = Modifier
        .clip(RoundedCornerShape(100))
        .background(
            when (job) {
                activities[0] -> blueIrrigation
                activities[1] -> redFertilizer
                activities[2] -> greenPesticide
                activities[3] -> Purple500
                else -> MainGreen
            }
        )
        .size(55.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun showGardenCard(){
    GardenCard(garden = Garden(
        0,
        "اکبری", 20, "25", "s", "", "",
        "",
        5.5,
    5.5,
        5.5,
        0
        )
    )
}