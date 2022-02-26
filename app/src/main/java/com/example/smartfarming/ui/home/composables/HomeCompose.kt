package com.example.smartfarming.ui.home.composables

import android.content.Intent
import android.util.Log
import android.widget.ImageButton
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.data.room.entities.Garden
import com.example.smartfarming.ui.addactivities.AddActivities
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.gardens.composables.GardenCard
import com.example.smartfarming.ui.home.HomeViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeCompose(viewModel : HomeViewModel){

    val context = LocalContext.current

    val gardensList by viewModel.getGardens().observeAsState()

    // FAB
    var fabExtended by remember {
        mutableStateOf(false)
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            MyFAB(context = context, fabExtended = fabExtended) {
                fabExtended =! fabExtended
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .height(250.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End

            ){
                ManageGardenPreview(gardensList)
            }
        }
    }
}

@Composable
fun ManageGardenPreview(gardenList : List<Garden>?){
    if (gardenList.isNullOrEmpty()){
            Image(
                painter = painterResource(id = R.drawable.sprout),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp)
            )

            Text(
                text = "اولین باغ خود را اضافه کنید",
                style = MaterialTheme.typography.body1,
                color = MainGreen
            )
    } else {
            Text(
                text = "باغداری شما",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(vertical = 3.dp, horizontal = 10.dp),
                color = MainGreen
            )
            LazyColumn(){
                items(gardenList){
                    GardenCard(garden = it)
                }
        }
    }
}

