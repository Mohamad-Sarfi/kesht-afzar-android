package com.example.smartfarming.ui.addgarden

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
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
    latLng: Map<String, String>
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
                text =  " موقعیت مکانی باغ " + gardenName,
                style = MaterialTheme.typography.body2
            )
            
            Text(
                text = latLng["lat"]!!,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(2.dp)
                )
            Text(
                text = latLng["long"]!!,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(2.dp)
            )
        }
        else {
            Text(
                text ="موقعیت باغ را مشخص کنید",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun displayStep3(){
    //AddGardenStep3()
}