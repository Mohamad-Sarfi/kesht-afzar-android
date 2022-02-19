package com.example.smartfarming.ui.addgarden

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.addactivities.ui.theme.lightGray

class AddGarden : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFarmingTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainGreen
                ) {
                    HostCompose()
                }
            }
        }
    }
}

@Composable
fun HostCompose(){
    val navController = rememberNavController()
    val viewModel : AddGardenViewModel = AddGardenViewModel()
    GardenNavGraph(navController = navController, viewModel)
}

@Composable
fun AddGardenCompose(
    viewModel: AddGardenViewModel,
    navController: NavHostController
){


    val context = LocalContext.current
    var step = viewModel.step.observeAsState()
    val gardenName by viewModel.gardenName.observeAsState("")
    val isLocationSet by viewModel.isLocationSet.observeAsState()
    val latLong by viewModel.location.observeAsState()

    val gardenAge by viewModel.getGardenAge().observeAsState()

    val varietiesList = viewModel.typeArray.observeAsState(arrayListOf())

    val irrigationCycle by viewModel.irrigationCycle.observeAsState()
    val irrigationDuration by viewModel.irrigationDuration.observeAsState()
    val irrigationVolume by viewModel.irrigationVolume.observeAsState()

    Scaffold(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize(1f),
        backgroundColor = MainGreen
    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .fillMaxSize(1f)) {

            val (topCard, middle, button) = createRefs()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .constrainAs(topCard) {
                        top.linkTo(parent.top, margin = 5.dp)
                    }
                    .fillMaxWidth()
            ) {
                Column {
                    ManageTopCard(step = step.value!!)
                }
            }

            Row(modifier = Modifier
                .constrainAs(middle) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topCard.bottom)
                    bottom.linkTo(button.top)
                }
                .fillMaxWidth(1f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                        28.dp
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    stepCircle(step = step.value!!, 1)
                    stepCircle(step = step.value!!, 2)
                    stepCircle(step = step.value!!, 3)
                    stepCircle(step = step.value!!, 4)
                }

                // Main column, consisting fields
                Column {

                    when(step.value){
                        1 -> AddGardenStep1(
                            gardenName!!, {viewModel.setGardenName(it)},
                            gardenAge!!, {viewModel.setGardenAge(it)},
                            varietiesList, {
                                viewModel.addType(it)
                            },{
                                viewModel.removeFromTypeArray(it)
                            }
                        )
                        2 -> AddGardenStep2(
                            irrigationDuration!!,
                            {viewModel.setIrrigationDuration(it)},
                            irrigationCycle!!,
                            {viewModel.setIrrigationCycle(it)},
                            irrigationVolume!!,
                            {viewModel.setIrrigationVolume(it)}
                        )
                        3 -> AddGardenStep3(navController, isLocationSet!!, gardenName, latLong!!)
                        else -> AddGardenStep4()

                    }
                }
            }

            Row(
                modifier = Modifier
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom, margin = 15.dp)
                    }
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = {
                        viewModel.decrementStep()
                              },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MainGreen,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 90.dp, height = 60.dp),
                    border = BorderStroke(2.dp, Color.White)
                )
                {
                    Text(
                        text = "قبلی",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(
                            vertical = 2.dp
                        )
                    )
                }

                Button(
                    onClick = {
                        viewModel.incrementStep()
                    },
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 180.dp, height = 60.dp)
                ) {
                    Text(
                        text = if (step.value!! < viewModel.MAX_STEPS) "ادامه" else "ثبت",
                        style = MaterialTheme.typography.h5,
                        color = MainGreen,
                        modifier = Modifier.padding(
                            vertical = 2.dp
                        )
                    )
                }
            }
        }

        }
    }

@Composable
fun stepCircle(
    step : Int,
    numberTag: Int
){
    val circle1Animation by animateDpAsState(
        targetValue = if (step == numberTag) 38.dp else 10.dp
    )
    val circleColor by animateColorAsState(
        if (step == numberTag) Color.White else lightGray
    )
    Column(modifier = Modifier
        .wrapContentSize(Alignment.Center)
        .padding(vertical = 18.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(6.dp)
                .size(width = 10.dp, height = circle1Animation)
                .clip(RoundedCornerShape(30.dp))
                .background(circleColor)
                .padding(14.dp)
        )
    }
}

@Composable
fun ManageTopCard(step: Int){
    Column() {

        Image(
            painter = painterResource(id = R.drawable.sprout_white),
            contentDescription = "",
            modifier = Modifier
                .padding(20.dp)
                .size(
                    130.dp
                )
                .align(CenterHorizontally)

        )

        Text(
            text = when(step){
                1 -> "ثبت باغ جدید"
                2-> "اطلاعات آبیاری باغ"
                3-> "موقعیت مکانی باغ"
                4 -> "نتایج آزمایش آب و خاک"
                else -> "ثبت باغ جدید" },
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    SmartFarmingTheme {
        //AddGardenCompose()
    }
}