package com.example.smartfarming.ui.addactivities

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.Shapes
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.addactivities.ui.theme.borderGray

@Composable
fun FertilizationBody(
    gardenName : String
){
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
                TitleCard(R.drawable.pesticide_colored)
            }
        }
        Row{
            Column(modifier = Modifier
                .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                SideBar()
            }

            Column(modifier = Modifier
                .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                FertilizationPart(gardenName)
            }
        }
    }
}


@Composable
fun TitleCard(imageID : Int){
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
            .background(Color(0xFFCAFFB2))
            .padding(start = 10.dp, top = 30.dp, end = 30.dp, bottom = (10).dp)
    ){
        Image(
            painter = painterResource(id = imageID),
            contentDescription = "pesticide",
            modifier = Modifier.size(150.dp)
        )

    }
}

@Composable
fun SideBar(){
    Column(
        modifier = Modifier
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
        Icon(
            painter = painterResource(id = R.drawable.irrigation),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(40.dp),
            tint = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.shovel),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(40.dp),
            tint = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.npk),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(40.dp),
            tint = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.pesticide1),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 35.dp)
                .size(40.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun FertilizationPart(
    gardenName: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(bottom = 20.dp)
    ) {
        var fertilizerName by remember{
            mutableStateOf("")
        }

        Row(
            modifier = Modifier
                .align(CenterHorizontally)
        ){
            Text(
                text = "$gardenName",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .align(CenterVertically),
                color = MainGreen
            )
            Text(
                text = " نام باغ: ",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(CenterVertically),
                color = MainGreen
            )
        }

        OutlinedTextField(
            value = fertilizerName,
            onValueChange = {
                fertilizerName = it
            },
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(15.dp)
            ,
            label = {
                Text(
                    "نام کود",
                    style = MaterialTheme.typography.body1
                )
            },
            shape = RoundedCornerShape(
                topEnd = 25.dp,
                topStart = 25.dp,
                bottomStart = 25.dp,
                bottomEnd = 0.dp
            )
        )

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 15.dp)
                .background(Color.White)
                .padding(vertical = 20.dp)
            ,
            shape = RoundedCornerShape(
                topEnd = 25.dp,
                topStart = 25.dp,
                bottomStart = 25.dp,
                bottomEnd = 0.dp
            ),
            border = BorderStroke(1.dp, borderGray)
        ) {
            Text(
                "تاریخ",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(7.dp),
                color = borderGray
            )
        }

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(
                topStart = 25.dp,
                bottomStart = 25.dp
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    start = 15.dp,
                    end = 0.dp,
                    bottom = 20.dp
                )
        ) {
            Text(
                text = "ثبت",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    top = 50.dp
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun defaultPreview4(){
    SmartFarmingTheme {
        FertilizationBody("باغ اکبر")
    }
}