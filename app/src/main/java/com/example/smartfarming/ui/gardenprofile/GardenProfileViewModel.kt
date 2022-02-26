package com.example.smartfarming.ui.gardenprofile

import androidx.lifecycle.*
import com.example.smartfarming.data.repositories.GardenRepo
import com.example.smartfarming.data.room.entities.Garden
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class GardenProfileViewModel(val repo : GardenRepo) : ViewModel() {

    private val garden = MutableLiveData<Garden>().apply {
        value = Garden(0, "", 0, "", "", "", "", "", 0.0, 0.0,
            0.0, 0)
    }

    fun getGardenByName(gardenName : String) {
        viewModelScope.launch(Dispatchers.Main) {
            garden.value  = repo.getGardenByName(gardenName)
        }
    }

    fun getGarden(gardenName : String) : MutableLiveData<Garden> {
        getGardenByName(gardenName)
        return garden
    }

}

class GardenProfileViewModelFactory(val repo: GardenRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GardenProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GardenProfileViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}