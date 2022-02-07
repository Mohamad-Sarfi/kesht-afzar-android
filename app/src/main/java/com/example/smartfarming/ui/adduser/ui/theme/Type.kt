package com.example.smartfarming.ui.adduser.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.example.smartfarming.R


val sina = FontFamily(
    Font(R.font.sina)
)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = sina,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    body2 = TextStyle(
        fontFamily = sina,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    h3 = TextStyle(
        fontFamily = sina,
        fontSize = 28.sp,
        textDirection = TextDirection.ContentOrRtl
    )
)

