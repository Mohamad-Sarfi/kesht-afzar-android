package com.example.smartfarming.ui.gardens

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartfarming.data.repositories.GardenRepo
import com.example.smartfarming.data.room.entities.Garden
import com.example.smartfarming.ui.addgarden.AddGardenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class GardensViewModel(val repo : GardenRepo) : ViewModel() {

    fun getGardens() : LiveData<List<Garden>> {
        var gardensList = liveData<List<Garden>>(){}
       viewModelScope.launch {
           gardensList = repo.getGardens().asLiveData()
       }

        Log.i("DBG", "${gardensList.value}")

        return gardensList
    }

}

class GardensViewModelFactory(private val repo: GardenRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GardensViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GardensViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}