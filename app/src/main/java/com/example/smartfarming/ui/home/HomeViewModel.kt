package com.example.smartfarming.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.smartfarming.data.repositories.GardenRepo
import com.example.smartfarming.data.room.entities.Garden
import com.example.smartfarming.ui.gardens.GardensViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class HomeViewModel(val repo : GardenRepo) : ViewModel() {

    fun getGardens() : LiveData<List<Garden>> {
        var gardensList = liveData<List<Garden>>(){}
        viewModelScope.launch {
            gardensList = repo.getGardens().asLiveData()
        }

        return gardensList
    }

}

class HomeViewModelFactory(val repo : GardenRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

