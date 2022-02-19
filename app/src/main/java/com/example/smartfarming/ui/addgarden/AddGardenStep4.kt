package com.example.smartfarming.ui.addgarden

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Soil, leaf and water analysis
@Composable
fun AddGardenStep4(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Filled.AddCircle, contentDescription = "",
                Modifier.padding(20.dp).size(50.dp)
            )
            Text(text = "آزمایش آب", style = MaterialTheme.typography.body2)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Filled.AddCircle, contentDescription = "",
                Modifier.padding(20.dp).size(50.dp)
                )
            Text(text = "آزمایش خاک", style = MaterialTheme.typography.body2)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Filled.AddCircle, contentDescription = "",
                Modifier.padding(20.dp).size(50.dp),
                )
            Text(text = "آزمایش برگ", style = MaterialTheme.typography.body2)
        }

    }
}