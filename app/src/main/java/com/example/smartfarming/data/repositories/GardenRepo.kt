package com.example.smartfarming.data.repositories

import androidx.annotation.WorkerThread
import com.example.smartfarming.data.room.daos.GardenDao
import com.example.smartfarming.data.room.entities.Garden
import kotlinx.coroutines.flow.Flow

class GardenRepo(
    private val gardenDao : GardenDao
) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getGardens() : Flow<List<Garden>> {
        return gardenDao.getAllGardens()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(garden: Garden){
        gardenDao.insert(garden)
    }


}