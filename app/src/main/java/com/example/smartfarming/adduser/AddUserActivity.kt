package com.example.smartfarming.adduser

import android.content.Context
import android.media.audiofx.AudioEffect
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.R
import androidx.compose.ui.unit.dp
import com.example.smartfarming.adduser.ui.theme.SmartFarmingTheme

class AddUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFarmingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AddUser()
                }
            }
        }
    }
}


@Composable
fun AddUser(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxWidth()
                .padding(horizontal = 35.dp, vertical = 45.dp)
            ,
        ) {
            Image(
                painter = painterResource(id = com.example.smartfarming.R.drawable.user),
                contentDescription = "User icon",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp)
            )
            Text(
                text = "کشت افزار",
                color = Color.White,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)

            )
            Text(
                text = "اپلیکشن مزرعه هوشمند",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)

            )
        }

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
        ) {

            // Username TextField
            var usernameText by remember {
                mutableStateOf("")
            }

            var passwordText by remember {
                mutableStateOf("")
            }

            var isUsernameEmpty by remember {
                mutableStateOf(false)
            }

            var isPassEmpty by remember {
                mutableStateOf(false)
            }

            var context = LocalContext.current



            OutlinedTextField(value = usernameText,
                onValueChange = {
                                usernameText = it
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White)
                    .width(300.dp)
                    .padding(vertical = 10.dp)
                ,
                label = {
                    Text(text = "نام کاربری", style = MaterialTheme.typography.body1)
                },
                textStyle = MaterialTheme.typography.body1,
                shape = MaterialTheme.shapes.medium,
                trailingIcon = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "icon",
                        tint = if (isUsernameEmpty) MaterialTheme.colors.error else MaterialTheme.colors.primary
                    )

                }
            )

            // Password textField

            OutlinedTextField(value = passwordText,
                onValueChange = {
                    passwordText = it
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White)
                    .width(300.dp)
                    .padding(vertical = 10.dp)
                ,
                label = {
                    Text(text = "رمز عبور", style = MaterialTheme.typography.body1)
                },
                textStyle = MaterialTheme.typography.body1,
                shape = MaterialTheme.shapes.medium,
                trailingIcon = {
                    Icon(Icons.Filled.Lock,
                        contentDescription = "icon",
                        tint = if (isPassEmpty) MaterialTheme.colors.error else MaterialTheme.colors.primary
                    )
                }

            )

            Text(
                text = "فراموشی رمز",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {
                        // Go to password recovery
                    }
                    .align(Alignment.CenterHorizontally)
                    .padding(6.dp)
            )

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ){

                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 80.dp, bottom = 20.dp)
                        .align(Alignment.CenterVertically)
                    ,
                    border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "ثبت نام", style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }

                Button(
                        onClick = {
                            isUsernameEmpty = usernameText.length < 4
                            isPassEmpty = passwordText.length < 8

                            if (isUsernameEmpty || isPassEmpty){
                                Toast.makeText(context, "نام کاربری و رمز عبور را صحیح وارد کنید", Toast.LENGTH_SHORT).show()
                            }
                        },
                modifier = Modifier
                    .width(150.dp)
                    .padding(top = 80.dp, bottom = 20.dp)
                ,
                shape = MaterialTheme.shapes.medium,

                ) {
                Text(text = "ورود",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(vertical = 2.dp)

                )
                }
                

                
            }
            
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "با حساب گوگل وارد شوید",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                
                Image(
                    painter = painterResource(id = com.example.smartfarming.R.drawable.search),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 5.dp)
                )
                
            }
            

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SmartFarmingTheme {
        AddUser()
    }
}