package com.example.smartfarming.ui.addactivities.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AvTimer
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.GardenTitle
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.borderGray
import com.example.smartfarming.ui.addactivities.ui.theme.greenPesticide
import kotlin.math.roundToInt

@Composable
fun IrrigationPart(gardenName: String){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        val (submitBtn, fields, title) = createRefs()

        Row(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top, margin = 30.dp)
                }
                .fillMaxWidth(1f),
        ) {
            GardenTitle(gardenName)
        }

        Column(modifier = Modifier
            .constrainAs(fields){
                top.linkTo(title.bottom, margin = 15.dp)
                bottom.linkTo(submitBtn.top)
            }) {
            IrrigationFields()
        }

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(
                topStart = 25.dp,
                bottomStart = 25.dp
            ),
            modifier = Modifier
                .constrainAs(submitBtn) {
                    bottom.linkTo(parent.bottom, margin = 15.dp)
                }
                .fillMaxWidth(1f)
                .padding(
                    start = 15.dp,
                    end = 0.dp,
                    bottom = 20.dp,
                    top = 60.dp
                ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MainGreen
            )
        ) {
            Text(
                text = "ثبت",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(
                    top = 10.dp
                ),
                color = Color.White
            )
        }

    }
}

@Composable
fun IrrigationFields() {

    var openDateDialogue by remember {
        mutableStateOf(false)
    }

    var sliderPosition by remember {
        mutableStateOf(0f)
    }

    var irrigationDate by remember {
        mutableStateOf(mutableMapOf<String, String>())
    }

    var irrigationDuration by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(
            horizontal = 10.dp
        )
    ) {

        OutlinedButton(
            onClick = { openDateDialogue = !openDateDialogue },
            modifier = Modifier
                .padding(18.dp)
                .width(FIELD_WIDTH)
                .height(FIELD_HEIGHT),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.White,
            ),
            border = BorderStroke(1.dp, MainGreen),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                bottomStart = 20.dp,
                topEnd = 20.dp,
                bottomEnd = 2.dp
            )
        ) {
            Text(
                text =
                if (irrigationDate["year"] == null) "تاریخ آبیاری" else
                    "${irrigationDate["year"]}/${irrigationDate["month"]}/${irrigationDate["day"]}",
                style = MaterialTheme.typography.body1,
                color = if (irrigationDate["year"] == null) borderGray else MainGreen,
                modifier = Modifier
            )
        }

        OutlinedTextField(
            value = irrigationDuration,
            onValueChange = {irrigationDuration = it },
            label = {
                Text(
                    text = "مدت آبیاری",
                    style = MaterialTheme.typography.body1,
                    color = borderGray
                )
            },
            modifier = Modifier
                .padding(18.dp)
                .width(FIELD_WIDTH)
            ,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                bottomStart = 20.dp,
                topEnd = 20.dp,
                bottomEnd = 2.dp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MainGreen,
                unfocusedBorderColor = greenPesticide
            ),
            maxLines = 1,
            singleLine = true,
            trailingIcon = {
                Icon(
                    Icons.Outlined.AvTimer,
                    contentDescription = "",
                    tint = MainGreen,
                    modifier = Modifier.size(30.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )

        )

        Text(
            text = "حجم آبیاری",
            style = MaterialTheme.typography.body2,
            color = borderGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )
        Slider(value = sliderPosition,
            onValueChange = { v ->
                val intValue = v.roundToInt()
                sliderPosition = intValue.toFloat()
            },
            valueRange = 1f .. 20f,
            steps = 20,
            colors = SliderDefaults.colors(
                thumbColor = MainGreen,
                activeTrackColor = MainGreen
            ),
            modifier = Modifier
                .padding(horizontal = 15.dp)
            ,
        )
        Text(
            text = sliderPosition.toString() + "  لیتر",
            style = MaterialTheme.typography.body1,
            color = borderGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        )

        if (openDateDialogue){
            DatePicker(openDateDialogue,
                {openDateDialogue = it},
                {date ->
                    irrigationDate = date
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun displayIrrigation(){
    IrrigationPart("باغ شماره 1")
}