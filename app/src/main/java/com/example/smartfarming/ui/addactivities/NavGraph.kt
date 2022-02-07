package com.example.smartfarming.ui.addactivities

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartfarming.ui.addactivities.viewModel.AddActivitiesViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    // Routes
    val homeActivity = ActivitiesScreen.HomeActivity.name
    val irrigation = ActivitiesScreen.Irrigation.name
    val fertilization = ActivitiesScreen.Fertilization.name


    NavHost(
        navController = navController,
        startDestination = ActivitiesScreen.HomeActivity.name){
            composable(
                route = ActivitiesScreen.HomeActivity.name
            ){
                val viewModel = viewModel<AddActivitiesViewModel>()
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
            route = "$fertilization/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                }
            )
        ){
            val gardenName = it.arguments?.getString("name")
            FertilizationBody(gardenName!!)
        }
    }
}