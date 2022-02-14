package com.example.smartfarming.data.daos

import androidx.room.*
import com.example.smartfarming.data.entities.Garden
import com.example.smartfarming.data.entities.Irrigation

@Dao
interface GardenDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertGarden(garden: Garden)

    @Update
    fun updateGarden(garden: Garden)

    @Delete
    fun deleteGarden(garden : Garden)

    @Query("SELECT * FROM Garden")
    fun loadAllGardens(): Array<Garden>

    @Query("SELECT * FROM Garden WHERE name = :gardenName")
    fun getGarden(gardenName : String) : Garden

    @Query("SELECT * FROM Garden WHERE age < 5")
    fun youngGardens()

    //@Query("SELECT Garden.name As gardenName, Irrigation.date FROM Garden, Irrigation WHERE Garden.name = Irrigation.gardenName")
    //fun gardenIrrigation(gardenName: String): Array<Irrigation>
}