package com.example.smartfarming.ui.addgarden

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.rounded.Cached
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.Park
import androidx.compose.material.icons.rounded.Pin
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.lightGray

@Composable
fun AddGardenStep1(
    gardenName: String,
    setName : (String) -> Unit,
    gardenAge : String,
    setAge : (String) -> Unit,
    varietiesList: State<List<String>>,
    addVariety: (String) -> Unit,
    removeVariety: (String) -> Unit
){
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = gardenName,
            onValueChange = {
                setName(it)
            },
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                textColor = Color.White,
                placeholderColor = Color.White,
                trailingIconColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            label = {
                Text(
                    "نام باغ",
                    style = MaterialTheme.typography.body1
                )
            },
            trailingIcon = {
                Icon(Icons.Rounded.Park, contentDescription = "")
            },
            modifier = Modifier
                .padding(10.dp)
                .size(width = 260.dp, height = 75.dp)
        )

        OutlinedTextField(
            value = gardenAge,
            onValueChange = {
                setAge(it)
            },
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                textColor = Color.White,
                placeholderColor = Color.White,
                trailingIconColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            label = {
                Text(
                    "سن باغ",
                    style = MaterialTheme.typography.body1
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            trailingIcon = {
                Icon(Icons.Rounded.Pin, contentDescription = "")
            },
            modifier = Modifier
                .padding(10.dp)
                .size(width = 260.dp, height = 75.dp)
        )

        PlanVarietySpinner(listOf()){
            addVariety(it)
        }

        plantVarietiesTexts(varietiesList){removeVariety(it)}



    }
}



@Composable
fun PlanVarietySpinner(
    varietiesList : List<String>,
    addVariety: (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    val myList = stringArrayResource(id = R.array.pistachio_types)

    Row(
        modifier = Modifier
            .padding(15.dp)
            .size(width = 260.dp, height = 62.dp)
            .clip(MaterialTheme.shapes.large)
            .background(lightGray)
            .clickable {
                expanded = !expanded
            }
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically)
    {
        Icon(
            Icons.Filled.ArrowDropDown,
            contentDescription = "",
            tint = MainGreen
        )
        Text(
            text = "پیوندهای باغ",
            style = MaterialTheme.typography.body1,
            color = MainGreen,
            modifier = Modifier
                .padding(start = 90.dp, end = 5.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            myList.forEach{
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        addVariety(it)
                        Log.i("VRTY", "$varietiesList")
                    })
                {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier
                            .padding(vertical = 3.dp, horizontal = 25.dp)
                    )

                }
            }
        }
    }
}

@Composable
fun plantVarietiesTexts(
    listt: State<List<String>>,
    removeVariety: (String) -> Unit
){
    Row(modifier = Modifier.padding(10.dp)) {
        listt.value.forEach{
            Text(
                text = it,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        removeVariety(it)
                    }

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun display(){
    //AddGardenStep1("", {}, "",{}, listOf(), {})
}