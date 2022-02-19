package com.example.smartfarming.ui.addgarden.GoogleMaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.addgarden.AddGardenCompose
import com.example.smartfarming.ui.addgarden.GoogleMap

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFarmingTheme() {
                androidx.compose.material.Surface(modifier = Modifier.background(Color.White)) {
                    Box(modifier = Modifier
                        .size(400.dp)
                        .padding(50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                    }
                }
            }
        }
                
        
    }
}