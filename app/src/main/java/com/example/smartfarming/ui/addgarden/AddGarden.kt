package com.example.smartfarming.ui.addgarden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
                    AddGardenCompose()
                }
            }
        }
    }
}

@Composable
fun AddGardenCompose(){
    val MAX_STEPS = 4
    var step by remember() {
        mutableStateOf(1)
    }
    var gardenName by remember {
        mutableStateOf("")
    }

    var gardenAge by remember {
        mutableStateOf("")
    }

    var varietiesList by remember {
        mutableStateOf(listOf<String>())
    }

    Scaffold(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize(1f)
            ,
        backgroundColor = MainGreen
    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .fillMaxSize(1f)) {

            val (topBar, middle, button) = createRefs()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .constrainAs(topBar) {
                        top.linkTo(parent.top, margin = 5.dp)
                    }
                    .fillMaxWidth()
            ) {
                Column {
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
                        text = "ثبت باغ جدید",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(10.dp)
                    )
                }
            }

            Row(modifier = Modifier
                .constrainAs(middle) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topBar.bottom)
                    bottom.linkTo(button.top)
                }
                .fillMaxWidth(1f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                        28.dp
                    ),
                ) {
                    stepCircle(step = step, 1)
                    stepCircle(step = step, 2)
                    stepCircle(step = step, 3)
                    stepCircle(step = step, 4)
                }
                Column {
                        AddGardenStep1(gardenName, {gardenName = it}, gardenAge, {gardenAge = it})
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
                    onClick = { step = if (step > 1) step - 1 else 1 },
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
                        step = if (step < MAX_STEPS) step + 1 else step
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
                        text = if (step < MAX_STEPS) "ادامه" else "ثبت",
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
        targetValue = if (step == numberTag) 34.dp else 10.dp
    )
    Column(modifier = Modifier
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .padding(6.dp)
                .size(width = 10.dp, height = circle1Animation)
                .clip(RoundedCornerShape(30.dp))
                .background(lightGray)
                .padding(14.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    SmartFarmingTheme {
        AddGardenCompose()
    }
}