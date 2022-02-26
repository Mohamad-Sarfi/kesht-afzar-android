package com.example.smartfarming.ui.home.composables

import android.content.Context
import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.ui.addactivities.AddActivities
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyFAB(
    context : Context,
    fabExtended : Boolean,
    switchFab : () -> Unit
){

    val fabWidthAnimate by animateDpAsState(
        if(fabExtended) 150.dp else 65.dp
    )

    FloatingActionButton(
        onClick = {
            if (!fabExtended) {
                switchFab()
            }
            else{
                val intent = Intent(context, AddActivities::class.java)
                context.startActivity(intent)
                switchFab()
            }
        },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.size(width = fabWidthAnimate, height = 65.dp),
        backgroundColor = MainGreen
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                fabExtended,
                enter = slideInHorizontally()
                        + expandHorizontally(
                    expandFrom = Alignment.End
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut()
            ) {
                Text(
                    text = "ثبت فعالیت",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(6.dp),
                    color = Color.White
                )
            }

            Icon(
                painterResource(id = R.drawable.shovel),
                contentDescription = "",
                modifier = Modifier
                    .padding(4.dp)
                    .size(32.dp),
                tint = Color.White
            )

        }
    }
}