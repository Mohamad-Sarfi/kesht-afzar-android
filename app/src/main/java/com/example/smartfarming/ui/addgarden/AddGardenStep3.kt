package com.example.smartfarming.ui.addgarden

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.SquareFoot
import androidx.compose.material.icons.rounded.Water
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.smartfarming.ui.addactivities.ActivitiesScreen
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng

@Composable
fun AddGardenStep3(
    navController: NavHostController,
    isLocationSet: Boolean,
    gardenName: String,
    latLng: Map<String, String>,
    area : Int,
    setArea: (Int) -> Unit
){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Icon(
            if (isLocationSet) Icons.Filled.LocationOn else Icons.Outlined.LocationOn,
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    navController.navigate(ActivitiesScreen.MapScreen.name)
                }
                .size(120.dp)
                .clip(RoundedCornerShape(100))
            ,
        )


        if (isLocationSet){
            Text(
                text =  "موقعیت مکانی باغ دریافت شد",
                style = MaterialTheme.typography.body1
            )

            OutlinedTextField(
                value = if (area != 0) area.toString() else "",
                onValueChange = {
                    if (!it.isDigitsOnly() || it == null || it == "") setArea(0)
                    else setArea(it.toInt())
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.White,
                    textColor = Color.White,
                    placeholderColor = Color.White,
                    trailingIconColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                shape = MaterialTheme.shapes.large,
                trailingIcon = {
                    Icon(Icons.Rounded.SquareFoot, contentDescription = "")
                },
                label = {
                    Text(
                        "وسعت باغ",
                        style = MaterialTheme.typography.body1
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.padding(vertical = 15.dp)
            )
            if (area != 0){
                Text(
                    text = "${area.toDouble()/10000}" + " هکتار",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
        else {
            Text(
                text ="موقعیت باغ را مشخص کنید",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(top = 50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun displayStep3(){
    //AddGardenStep3()
}