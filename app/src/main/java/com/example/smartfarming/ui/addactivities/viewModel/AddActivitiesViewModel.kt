package com.example.smartfarming.ui.addactivities.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.smartfarming.data.repositories.GardenRepo
import com.example.smartfarming.data.room.entities.Garden
import com.example.smartfarming.ui.addgarden.AddGardenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddActivitiesViewModel(val repo : GardenRepo) : ViewModel() {



    fun getGardens() : LiveData<List<Garden>>{
        var list = liveData<List<Garden>> {  }

        viewModelScope.launch() {
            list = repo.getGardens().asLiveData()
        }

        return list
    }


}

class AddActivitiesViewModelFactory(private val repo : GardenRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddActivitiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddActivitiesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}