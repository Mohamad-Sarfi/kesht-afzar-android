package com.example.smartfarming.ui.addactivities

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartfarming.ui.addactivities.viewModel.AddActivitiesViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: AddActivitiesViewModel
){
    // Routes
    val homeActivity = ActivitiesScreen.HomeActivity.name
    val irrigation = ActivitiesScreen.Irrigation.name
    val activityScreen = ActivitiesScreen.ActivityScreen.name


    NavHost(
        navController = navController,
        startDestination = ActivitiesScreen.HomeActivity.name){
            composable(
                route = ActivitiesScreen.HomeActivity.name
            ){
                AddActivitiesMain(navController, viewModel)
            }
            composable(
                route = "$irrigation/{name}",
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                    }
                )
            ){ entry ->
                val gardenName = entry.arguments?.getString("name")
                IrrigationBody(gardenName!!)
            }

        composable(
            route = "$activityScreen/{gardenName}/{act}",
            arguments = listOf(
                navArgument("gardenName"){
                    type = NavType.StringType
                },
                navArgument("act"){
                    type = NavType.StringType
                }
            )
        ){entry ->
            val gardenName = entry.arguments?.getString("gardenName")
            val act = entry.arguments?.getString("act")
            HostActivity(gardenName!!, act!!)
        }
    }
}