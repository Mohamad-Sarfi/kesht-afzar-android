package com.example.smartfarming.ui.addgarden

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.smartfarming.data.repositories.GardenRepo
import com.example.smartfarming.data.room.entities.Garden
import com.google.android.libraries.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddGardenViewModel(val repo : GardenRepo) : ViewModel() {
    val MAX_STEPS = 4

    val typeArray = MutableLiveData<List<String>>().apply {
        value = listOf()
    }
    var gardenName = MutableLiveData<String>().apply {
        value = ""
    }

    private var gardenAge = MutableLiveData<String>().apply {
        value = ""
    }

    var location = MutableLiveData<Map<String, String>>().apply {
        value = mutableMapOf("lat" to "0", "long" to "0")
    }

    var irrigationDuration = MutableLiveData<String>().apply {
        value = ""
    }

    var irrigationCycle = MutableLiveData<String>().apply {
        value = ""
    }
    var irrigationVolume = MutableLiveData<String>().apply {
            value = ""
    }

    var step = MutableLiveData<Int>().apply {
        value = 1
    }

    var isLocationSet = MutableLiveData<Boolean>().apply{
        value = false
    }

    private var locationList = MutableLiveData<List<LatLng>>().apply {
        value = listOf()
    }

    private val gardenArea = MutableLiveData<Int>().apply {
        value = 0
    }


    fun getArea() : MutableLiveData<Int> = gardenArea
    fun setArea(area : Int){
        gardenArea.value = area
    }

    fun incrementStep(){
        if (step.value!! < MAX_STEPS){
            step.value = step.value!! + 1
        }
    }

    fun decrementStep(){
        if (step.value!! > 1){
            step.value = step.value!! - 1
        }
    }


    fun addType(newType : String){
        var array : List<String>? = typeArray.value
        if (newType !in array!! && array.size < 5){
                array = array + listOf(newType)
        }
        typeArray.value = array
    }

    fun removeFromTypeArray(item : String){
        val index = typeArray.value!!.indexOf(item)
        val tempArray = typeArray.value!!.toMutableList().also {
            it.removeAt(index)
        }
        typeArray.value = tempArray
    }


    fun setGardenName(name : String) {
        gardenName.value = name
    }



    fun setGardenAge(age : String){
        gardenAge.value = age
    }

    fun getGardenAge() : MutableLiveData<String> = gardenAge

    fun setLocation(lat : String, lon : String){
        location.value = mutableMapOf("lat" to lat.substring(0,6), "long" to lon.substring(0,6))
    }

    fun setLocationList(locations : List<LatLng>){
        locationList.value = locations
        location.value = mutableMapOf("lat" to locations[0].latitude.toString().substring(0,6), "long" to locations[0].longitude.toString().substring(0,6))
    }

    fun getLocationList() : List<LatLng> = locationList.value!!

    fun setIrrigationDuration(duration : String){
        irrigationDuration.value = duration
    }

    fun setIrrigationCycle(cycle : String){
        irrigationCycle.value = cycle
    }

    fun setIrrigationVolume(volume : String){
        irrigationVolume.value = volume
    }



    // Database
    fun addGardenToDb(garden : Garden){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(garden)
        }
    }

    fun getGardens() : LiveData<List<Garden>>{
        var list = liveData<List<Garden>> {  }
        viewModelScope.launch(Dispatchers.IO) {
            list = repo.getGardens().asLiveData()
        }
        return list
    }

}

class AddGardenViewModelFactory(private val repo : GardenRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddGardenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddGardenViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}