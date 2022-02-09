package com.example.smartfarming.ui.addactivities

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.addactivities.ui.theme.waterBlueDark
import com.example.smartfarming.ui.addactivities.ui.theme.blueIrrigation
import kotlin.math.roundToInt

@Composable
fun IrrigationBody(gardenNameArg : String){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Column() {
            TitleCard(gardenName = gardenNameArg)
            IrrigationDetails()
        }


    }
}

@Composable
fun TitleCard(gardenName : String){
    Box(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.large)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        blueIrrigation,
                        waterBlueDark
                    )
                )
            )
            .fillMaxWidth(1f)

            .padding(vertical = 30.dp, horizontal = 10.dp),

        ){
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.watering),
                contentDescription = "Watering icon",
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)
            )

            Text(
                text =  "آبیاری باغ " + gardenName ,
                color = Color.White,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun IrrigationDetails(){
    Column(
        Modifier.fillMaxWidth(2f)
    ) {
        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(20.dp)
        ) {

            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(3.dp, color = blueIrrigation),
                modifier = Modifier.size(width = 180.dp, height = 50.dp)

            ) {
                Text(
                    text = "تاریخ آبیاری",
                    style = MaterialTheme.typography.body2 ,
                    color = blueIrrigation,
                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 30.dp)
                )
            }

            Icon(
                Icons.Outlined.DateRange,
                contentDescription = "date icon",
                tint = blueIrrigation,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(50.dp)
                    .align(CenterVertically)
            )
        }

        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(20.dp)
        ) {

            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(3.dp, color = blueIrrigation),
                modifier = Modifier.size(width = 180.dp, height = 50.dp)

            ) {
                Text(
                    text = "ساعات آبیاری",
                    style = MaterialTheme.typography.body2 ,
                    color = blueIrrigation,

                )
            }

            Icon(
                Icons.Outlined.PlayArrow,
                contentDescription = "date icon",
                tint = blueIrrigation,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(50.dp)
                    .align(CenterVertically)
            )
        }

        WaterAmountSlider()
        Additionals()

        //Submit Button
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 40.dp, horizontal = 30.dp)
                .size(width = 220.dp, 55.dp)
                .padding(vertical = 3.dp, horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = blueIrrigation,
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.large


        ) {
            Text(
                text = "ثبت",
                style = MaterialTheme.typography.body2,
            )
        }

    }
}

@Composable
fun WaterAmountSlider(){
    var sliderPosition by remember {
        mutableStateOf(0f)
    }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        Text(
            text = "حجم آبیاری",
            style = MaterialTheme.typography.body2,
            color = blueIrrigation,
            modifier = Modifier.align(CenterHorizontally)
        )

        Slider(value = sliderPosition,
            onValueChange = { v ->
                val intValue = v.roundToInt()
                sliderPosition = intValue.toFloat()
            },
            valueRange = 1f .. 20f,
            steps = 20,
            colors = SliderDefaults.colors(
                thumbColor = waterBlueDark,
                activeTrackColor = blueIrrigation
            ),
            modifier = Modifier.padding(horizontal = 30.dp),
        )
        Text(
            text = sliderPosition.toString() + "  لیتر",
            style = MaterialTheme.typography.body2,
            color = blueIrrigation,
            modifier = Modifier.align(CenterHorizontally)
        )

    }

}


@Composable
fun Additionals(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(3.dp, color = blueIrrigation),
                modifier = Modifier
                    .size(width = 200.dp, height = 50.dp)
                    .align(CenterHorizontally)

            ) {
                Text(
                    text = "کودهای محلول در آب",
                    style = MaterialTheme.typography.body2,
                    color = blueIrrigation,
                    )
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    SmartFarmingTheme {
        IrrigationBody(gardenNameArg = "نام باغ")
    }
}