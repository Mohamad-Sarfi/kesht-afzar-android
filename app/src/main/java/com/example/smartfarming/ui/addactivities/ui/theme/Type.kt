package com.example.smartfarming.ui.addactivities.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.example.smartfarming.ui.adduser.ui.theme.sina



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
    ),
    h4 = TextStyle(
        fontFamily = sina,
        fontSize = 25.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    h5 = TextStyle(
        fontFamily = sina,
        fontSize = 23.sp,
        textDirection = TextDirection.ContentOrRtl
    )
)