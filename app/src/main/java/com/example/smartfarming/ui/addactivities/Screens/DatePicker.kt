package com.example.smartfarming.ui.addactivities.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.borderGray
import com.example.smartfarming.ui.addactivities.ui.theme.lightGray

@Composable
fun DatePicker(
    openDialogue : Boolean,
    changeOpenDialogue : (Boolean) -> Unit
){
    var date by remember {
        mutableStateOf("")
    }
    var month by remember {
        mutableStateOf("")
    }
    var year by remember {
        mutableStateOf("1400")
    }

    val daysList = List<String>(31){
        (it + 1).toString()
    }

    val monthList = List<String>(12){
        (it + 1).toString()
    }

    val yearList = List<String>(10){
        (it + 1398).toString()
    }

    Dialog(
        onDismissRequest = { changeOpenDialogue(false) }
    ) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(350.dp)
                .padding(10.dp)
            ,
            shape = RoundedCornerShape(15.dp),
            color = Color.White
        ) {
            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DateSpinner(daysList, "روز")
                DateSpinner(rangeList = monthList, titleText = "ماه")
                DateSpinner(rangeList = yearList, titleText = "سال" )
            }
        }
    }

}


@Composable
fun DateSpinner(
    rangeList : List<String>,
    titleText : String
){
    var current by remember {
        mutableStateOf(rangeList[0])
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){

        Row(
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(110.dp)
                .background(lightGray)
                .clickable {
                    expanded = !expanded
                }
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MainGreen
            )
            Text(
                text = current,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 15.dp, end = 5.dp)
            )


            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                rangeList.forEach{
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            current = it
                        })
                    {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1
                        )

                    }
                }
            }

        }
        Text(
            text = titleText,
            style = MaterialTheme.typography.body2,
            color = MainGreen
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DisplayDialog(){
    DatePicker(true, {})
}