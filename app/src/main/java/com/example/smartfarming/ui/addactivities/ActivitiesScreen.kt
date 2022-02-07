package com.example.smartfarming.ui.addactivities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

enum class ActivitiesScreen(
    val icon : ImageVector,
) {

    HomeActivity(
        icon = Icons.Filled.Home
    ),

    Irrigation(
        icon = Icons.Filled.Check
    ),

    Fertilization(
        icon = Icons.Filled.Build
    )

    ;

    @Composable
    fun content(onScreenChange: (String) -> Unit) {

    }
}

