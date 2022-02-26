package com.example.smartfarming.ui.addgarden

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.rounded.Water
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.LightGray

// Irrigation cycle
@Composable
fun AddGardenStep2(
    duration : String,
    setDuration : (String) -> Unit,
    cycle : String,
    setCycle: (String) -> Unit,
    volume: String,
    setVolume: (String) -> Unit
){
    var final_note_show by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = duration,
            onValueChange = {
                if (it.length < 4){
                    setDuration(it)
                }
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
                    "ساعت",
                    style = MaterialTheme.typography.body1
                )
            },
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                Icon(Icons.Rounded.Timer, contentDescription = "")
            },
            modifier = Modifier
                .padding(10.dp)
                .size(width = 260.dp, height = 75.dp)
        )

        IrrigationCycleSpinner({ final_note_show = it }){setCycle(it)}

        OutlinedTextField(
            value = volume,
            onValueChange = {
                if (it.length < 3){
                    setVolume(it)
                }
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
                    "حجم آب (لیتر)",
                    style = MaterialTheme.typography.body1
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                Icon(Icons.Rounded.Water, contentDescription = "")
            },
            modifier = Modifier
                .padding(10.dp)
                .size(width = 260.dp, height = 75.dp)
        )

        if (final_note_show){
            Row(){
                Text(
                    text = "با حجم  " + volume + " لیتر",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "به مدت " + duration + " ساعت",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = " دوره آبیاری " + cycle + "ه",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(2.dp)
                )


            }
        }

    }
}


@Composable
fun IrrigationCycleSpinner(
    setShowToggle: (Boolean) -> Unit,
    setCycle : (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    val arrayString = stringArrayResource(id = R.array.irrigation_cycle)

    Row(
        modifier = Modifier
            .padding(15.dp)
            .size(width = 260.dp, height = 62.dp)
            .clip(MaterialTheme.shapes.large)
            .background(LightGray)
            .clickable {
                expanded = !expanded
            }
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically)
    {
        Icon(
            Icons.Filled.ArrowDropUp,
            contentDescription = "",
            tint = MainGreen
        )
        Text(
            text = "مدار آبیاری",
            style = MaterialTheme.typography.body1,
            color = MainGreen,
            modifier = Modifier
                .padding(start = 90.dp, end = 5.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            arrayString.forEach{
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        setShowToggle(false)
                        setCycle(it)
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