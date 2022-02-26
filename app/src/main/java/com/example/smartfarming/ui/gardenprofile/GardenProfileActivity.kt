package com.example.smartfarming.ui.gardenprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartfarming.FarmApplication
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.gardens.composables.GardenProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GardenProfileActivity : ComponentActivity() {

    // Get gardenName from intent


    val viewModel : GardenProfileViewModel by viewModels {
        GardenProfileViewModelFactory((application as FarmApplication).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inputIntent = intent
        val gardenName = inputIntent.getStringExtra("gardenName")

        val garden = viewModel.getGarden(gardenName!!)

        setContent {
            SmartFarmingTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val garden = viewModel.getGarden(gardenName).observeAsState()
                    GardenProfile(garden)
                }
            }
        }
    }
}

fun getGarden(){

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    SmartFarmingTheme {

    }
}