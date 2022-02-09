package com.example.smartfarming.ui.addactivities.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.smartfarming.ui.addactivities.GardenTitle
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.borderGray
import com.example.smartfarming.ui.addactivities.ui.theme.greenPesticide
import com.example.smartfarming.ui.addactivities.ui.theme.lightGreenPesticide

@Composable
fun FertilizationPart(
    gardenName: String
){



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
            FertilizationFields()
        }

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(
                topStart = 25.dp,
                bottomStart = 25.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = greenPesticide,
                contentColor = Color.White
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
                )
        ) {
            Text(
                text = "ثبت",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(
                    top = 10.dp
                )
            )
        }

    }
}

@Composable
fun FertilizationFields(){

    var openDateDialogue by remember {
        mutableStateOf(false)
    }

    var fertilizerName by remember{
        mutableStateOf("")
    }

    var fertilizerExplain by remember {
        mutableStateOf("")
    }

    var fertilizationDate by remember {
        mutableStateOf(mutableMapOf<String, String>())
    }

    Column(
        modifier = Modifier.padding(
            horizontal = 10.dp
        )
    ){


        OutlinedTextField(
            value = fertilizerName,
            onValueChange = {fertilizerName = it},
            label = {
                Text(
                    text = "نام کود",
                    style = MaterialTheme.typography.body1,
                    color = borderGray
                )
            },
            modifier = Modifier
                .padding(18.dp)
                .width(200.dp)
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
            )

        )

        OutlinedButton(
            onClick = { openDateDialogue = !openDateDialogue },
            modifier = Modifier
                .padding(18.dp)
                .width(200.dp),
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
                if (fertilizationDate["year"] == null) "تاریخ تغذیه" else
                "${fertilizationDate["year"]}/${fertilizationDate["month"]}/${fertilizationDate["day"]}",
                style = MaterialTheme.typography.body1,
                color = borderGray,
                modifier = Modifier
                    .padding(vertical = 6.dp)
            )
        }

        if (openDateDialogue){
            DatePicker(openDateDialogue,
                {openDateDialogue = it},
                {date ->
                    fertilizationDate = date
                }
            )
        }

        OutlinedTextField(
            value = fertilizerExplain,
            onValueChange = {fertilizerExplain = it},
            label = {
                Text(
                    text = "توضیحات",
                    style = MaterialTheme.typography.body1,
                    color = borderGray
                )
            },
            modifier = Modifier
                .padding(18.dp)
                .width(200.dp)
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
            )

        )

    }
}




@Preview(showBackground = true)
@Composable
fun DisplayFertilization(){
    FertilizationPart("اکبر")
}

