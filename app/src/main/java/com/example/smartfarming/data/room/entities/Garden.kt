package com.example.smartfarming.data.room.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "garden_table")
data class Garden(
    @PrimaryKey(autoGenerate = true) @ColumnInfo
        (name = "rowid") val id : Int,
    @NonNull @ColumnInfo
        (name = "name") val name: String,
    @ColumnInfo
        (name = "age") val age: Int,
    @ColumnInfo
        (name = "lat_long") val lat_long: String,
    @ColumnInfo
        (name = "plant_type") val plant_type: String,
    @ColumnInfo
        (name = "plant_varieties") val plant_varieties: String,
    @ColumnInfo
        (name = "soil_type") val soil_type : String,
    @ColumnInfo
        (name = "irrigation_type") val irrigation_type : String,
    @ColumnInfo
        (name = "irrigation_duration") val irrigation_duration : Double,
    @ColumnInfo
        (name = "irrigation_volume") val irrigation_volume : Double,
    @ColumnInfo
        (name = "area") val area : Double,
    @ColumnInfo
        (name = "user_id") val user_id: Int
) {
}