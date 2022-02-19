package com.example.smartfarming.ui.addgarden

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smartfarming.ui.addactivities.ActivitiesScreen

@Composable
fun GardenNavGraph(navController: NavHostController, viewModel: AddGardenViewModel){
    val addGarden = ActivitiesScreen.AddGardenScreen.name
    val maps = ActivitiesScreen.MapScreen.name

    NavHost(
        navController = navController, startDestination = addGarden
    ){
        composable(
            route = addGarden
        ){
            AddGardenCompose(navController = navController, viewModel = viewModel)
        }

        composable(
            route = maps
        ){
            MapCompose(navController = navController, viewModel = viewModel)
        }
    }
}