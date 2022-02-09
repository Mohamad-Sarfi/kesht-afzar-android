package com.example.smartfarming.ui.addactivities.Screens

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import com.example.smartfarming.ui.addactivities.ui.theme.lightGray
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Composable
fun DatePicker(
    openDialogue : Boolean,
    changeOpenDialogue : (Boolean) -> Unit,
    updateDate: (MutableMap<String, String>) -> Unit
){
    val dateMap = getDate()

    var day = remember {
        mutableStateOf(dateMap["day"])
    }
    var month = remember {
        mutableStateOf(dateMap["month"])
    }
    var year = remember {
        mutableStateOf(dateMap["year"])
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
                .height(390.dp)
                .padding(10.dp)
            ,
            shape = RoundedCornerShape(15.dp),
            color = Color.White
        ) {
            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .padding(10.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DateSpinner(daysList, "روز", day){day.value = it}
                DateSpinner(rangeList = monthList, titleText = "ماه", month){month.value = it}
                DateSpinner(rangeList = yearList, titleText = "سال" , year){year.value = it}

                Button(
                    onClick = {
                        changeOpenDialogue(false)
                        updateDate(mutableMapOf("day" to day.value!!, "month" to month.value!!, "year" to year.value!!))
                              },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "تایید",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

}


@Composable
fun DateSpinner(
    rangeList : List<String>,
    titleText : String,
    defaultValue : MutableState<String?>,
    returnValue : (String) -> Unit
){
    var current by remember {
        mutableStateOf(defaultValue.value)
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
                text = current!!,
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
                            returnValue(current!!)
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



fun getDate(): Map<String, String>{
    val date = Calendar.getInstance()
    var month = 0
    Log.i("myDate" ,"$month")

    val year = if (date[Calendar.MONTH] < 4){
        date[Calendar.YEAR] - 622
    }
    else {
        date[Calendar.YEAR] - 623
    }

    if (date[Calendar.DAY_OF_MONTH] < 21){
        month = when(date[Calendar.MONTH]){
            0 -> 10
            1 -> 11
            2 -> 12
            3 -> 1
            4 -> 2
            5 -> 3
            6 -> 4
            7 -> 5
            8 -> 6
            9 -> 7
            10 -> 8
            11 -> 9
            else -> 1
        }
    }
    else {
        month = when(date[Calendar.MONTH]){
            0 -> 11
            1 -> 12
            2 -> 1
            3 -> 2
            4 -> 3
            5 -> 4
            6 -> 5
            7 -> 6
            8 -> 7
            9 -> 8
            10 -> 9
            11 -> 10
            else -> 1
        }
    }

    return mapOf("year" to year.toString(), "month" to month.toString(), "day" to date[Calendar.DAY_OF_MONTH].toString())
}


@Preview(showBackground = true)
@Composable
fun DisplayDialog(){
    DatePicker(true, {}, {})
}