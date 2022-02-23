package com.example.smartfarming.ui.home.composables

import android.content.Intent
import android.widget.ImageButton
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartfarming.R
import com.example.smartfarming.data.room.entities.Garden
import com.example.smartfarming.ui.addactivities.AddActivities
import com.example.smartfarming.ui.addactivities.ui.theme.MainGreen
import com.example.smartfarming.ui.home.HomeViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeCompose(viewModel : HomeViewModel){

    val context = LocalContext.current

    val gardensList by viewModel.getGardens().observeAsState()

    // FAB
    var fabExtended by remember {
        mutableStateOf(false)
    }
    val fabWidthAnimate by animateDpAsState(
        if(fabExtended) 150.dp else 65.dp
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (!fabExtended) {
                        fabExtended = !fabExtended
                    }
                    else{
                        val intent = Intent(context, AddActivities::class.java)
                        context.startActivity(intent)
                        fabExtended = !fabExtended
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
    ) {



    }
}

@Composable
fun ManageGardenPreview(gardenList : List<Garden>){
    if (gardenList.isEmpty()){
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sprout),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp)
            )

            Text(
                text = "اولین باغ خود را اضافه کنید",
                style = MaterialTheme.typography.body1,
                color = MainGreen
            )

        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(250.dp)
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}

