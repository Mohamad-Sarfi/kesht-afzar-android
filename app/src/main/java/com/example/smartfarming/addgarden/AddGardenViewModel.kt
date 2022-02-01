package com.example.smartfarming.addgarden

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddGardenViewModel : ViewModel() {
    val typeArray = MutableLiveData<MutableList<String>>().apply {
        value = arrayListOf()
    }
    private var gardenName = MutableLiveData<String>().apply {
        value = ""
    }

    private var gardenAge = MutableLiveData<String>().apply {
        value = "0"
    }

    private var location = MutableLiveData<Map<String, String>>().apply {
        value = mutableMapOf("lat" to "", "long" to "")
    }



    fun addType(newType : String){
        val array : MutableList<String>? = typeArray.value
        array!!.add(newType)
        typeArray.value = array
    }

    fun removeFromTypeArray(item : String){
        val tempArray = typeArray.value
        for (i in 0 .. tempArray!!.size){
            if (tempArray[i] == item) {
                tempArray.removeAt(i)
                break
            }
        }
        typeArray.value = tempArray
    }

    fun getTypeArray() : LiveData<MutableList<String>> {
        return typeArray
    }

    fun setGardenName(name : String){
        gardenName.value = name
    }

    fun getGardenName() : String? = gardenName.value


    fun setGardenAge(age : String){
        gardenAge.value = age
    }

    fun getGardenAge() : String? = gardenAge.value

    fun setLocation(lat : String, lon : String){
        location.value = mutableMapOf("lat" to lat, "long" to lon)
    }

}