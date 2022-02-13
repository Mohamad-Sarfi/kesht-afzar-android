package com.example.smartfarming.ui.addactivities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.ui.theme.SmartFarmingTheme
import com.example.smartfarming.ui.addactivities.viewModel.AddActivitiesViewModel
import com.example.smartfarming.ui.adduser.ui.theme.fertilizer
import com.example.smartfarming.ui.adduser.ui.theme.pesticide
import com.example.smartfarming.ui.adduser.ui.theme.watering
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController


class AddActivities : ComponentActivity() {


    lateinit var viewModel : AddActivitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFarmingTheme {
                viewModel = ViewModelProvider(this).get(AddActivitiesViewModel::class.java)
                HostComposable(viewModel)
            }
        }
    }
}

@Composable
fun HostComposable(viewModel : AddActivitiesViewModel){
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}

@Composable
fun AddActivitiesMain(navController : NavHostController, viewModel : AddActivitiesViewModel){

    val gardensList : List<String> by viewModel.gardenList.observeAsState(listOf())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Cards(
            navController,
            gardensList
        )
    }
}



@Composable
fun Cards(
    navController: NavHostController,
    garensList : List<String>
){

    var currentGarden by remember {
        mutableStateOf(garensList[0])
    }

    var clicked = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 50.dp)
    ) {
        Text(
            text = "افزودن فعالیت جدید",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(35.dp)
            ,
            color = MaterialTheme.colors.primary
        )
        Row(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Card("آبیاری", R.drawable.watering, watering, clicked) {
                clicked.value = !clicked.value
                navigateToScreens(navController, currentGarden, ActivitiesScreen.IrrigationBody.name)
            }
            Card("تغذیه", R.drawable.npk, fertilizer, clicked) {
                clicked.value = !clicked.value
                navigateToScreens(navController, currentGarden, ActivitiesScreen.FertilizationBody.name)
            }
        }
        Row(
            modifier = Modifier
                .padding(2.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Card("محلول پاشی", R.drawable.pesticide1, pesticide,clicked ){
                clicked.value = !clicked.value
                navigateToScreens(navController, currentGarden, ActivitiesScreen.PesticideBody.name)
            }
            Card("سایر", R.drawable.shovel, MaterialTheme.colors.primary, clicked) {
                clicked.value = !clicked.value
                navigateToScreens(navController, currentGarden, ActivitiesScreen.PesticideBody.name)
            }
        }
        Row(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Column() {
                Text(
                    text = "باغ مورد نظر را انتخاب کنید",
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.body1
                    )
                GardenSpinner(garensList, currentGarden){
                    currentGarden = it
                }

            }
        }
    }
}


@Composable
fun Card(
    text : String,
    iconId : Int,
    color: Color,
    clicked: MutableState<Boolean>,
    action : () -> Unit
){
    val cardSize by animateDpAsState(
        if (clicked.value) 0.dp else 160.dp
    )

    Column(
        modifier = Modifier
            .padding(10.dp)
            .size(cardSize)
            .clip(shape = MaterialTheme.shapes.large)
            .shadow(3.dp)
            .background(color)
            .clickable { action() }
            .padding(30.dp)
        

        ,
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Icon",
            tint = Color.White,
            modifier = Modifier
                .padding(5.dp)
                .size(55.dp)
                .align(Alignment.CenterHorizontally)

        )
        Text(
            text = text,
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 5.dp),
            style = MaterialTheme.typography.body2
        )
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun GardenSpinner(
    gardensList : List<String>,
    currentGarden : String,
    updateCurrentGarden : (garden : String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "transition")
    val arrowRotateDegree by transition.animateFloat({
        tween(delayMillis = 50)
    }, label = "rotationDegree") {
        if (expanded) 180f else 0f
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth(1f),
    ){
        Row(modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .clip(shape = MaterialTheme.shapes.large)
            .shadow(elevation = 3.dp)
            .background(Color(0xFFEEEEEE))
            .clickable {
                expanded = !expanded
            }
            .padding(vertical = 10.dp, horizontal = 30.dp)
            .align(Alignment.Center)

            ,
            ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        end = 90.dp
                    )
                    .size(55.dp)
                    .rotate(arrowRotateDegree)
                ,

            )
            Text(
                text = currentGarden,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(5.dp)
                )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                gardensList.forEach { garden ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        updateCurrentGarden(garden)

                    }) {
                        Text(
                            text = garden,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 20.dp),
                            style = MaterialTheme.typography.body2
                        )
                    }

                }
            }
        }
    }
}

// Navigation
private fun navigateToScreens(
    navController: NavHostController,
    gardenName: String,
    activityName: String
){
    val route = ActivitiesScreen.ActivityScreen.name
    val icon =
        when(activityName){
            ActivitiesScreen.FertilizationBody.name -> ActivitiesScreen.FertilizationBody.icon
            ActivitiesScreen.IrrigationBody.name -> ActivitiesScreen.IrrigationBody.icon
            ActivitiesScreen.PesticideBody.name -> ActivitiesScreen.PesticideBody.icon
            ActivitiesScreen.OtherActivityBody.name -> ActivitiesScreen.OtherActivityBody.icon
            else -> ActivitiesScreen.OtherActivityBody.icon
        }
    navController.navigate("$route/$gardenName/$activityName")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SmartFarmingTheme {
        //Cards(navController = rememberNavController())
    }
}