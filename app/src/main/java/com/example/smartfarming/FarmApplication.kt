package com.example.smartfarming

import android.app.Application
import com.example.smartfarming.data.repositories.GardenRepo
import com.example.smartfarming.data.room.GardenDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FarmApplication : Application() {

    private val database : GardenDb by lazy { GardenDb.getDatabase(this) }
    val repo by lazy { GardenRepo(database.gardenDao()) }

}