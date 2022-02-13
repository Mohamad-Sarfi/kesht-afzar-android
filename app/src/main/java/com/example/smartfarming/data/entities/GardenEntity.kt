package com.example.smartfarming.data.entities

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity(primaryKeys = ["id", "name"])
data class GardenEntity(

    val id : Int,
    val name : String,
    val age : Int,
    val area : String,
    val lat : Float,
    val long: Float,
    val varieties: List<String>,
    val plant : String
)