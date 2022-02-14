package com.example.smartfarming.data.entities

import androidx.room.PrimaryKey

data class Irrigation(
    @PrimaryKey val id : Int,

    val gardenName: String,
    val date : String,
    val volume: Int,
    val duration : Int,
    val additions : List<String>
)