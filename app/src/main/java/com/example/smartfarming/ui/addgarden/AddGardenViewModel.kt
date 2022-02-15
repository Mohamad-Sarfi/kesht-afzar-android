package com.example.smartfarming.ui.addgarden

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddGardenViewModel : ViewModel() {
    val typeArray = MutableLiveData<List<String>>().apply {
        value = listOf()
    }
    var gardenName = MutableLiveData<String>().apply {
        value = ""
    }

    private var gardenAge = MutableLiveData<String>().apply {
        value = "0"
    }

    private var location = MutableLiveData<Map<String, String>>().apply {
        value = mutableMapOf("lat" to "", "long" to "")
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

    fun getGardenAge() : String? = gardenAge.value

    fun setLocation(lat : String, lon : String){
        location.value = mutableMapOf("lat" to lat, "long" to lon)
    }

}