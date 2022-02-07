package com.example.smartfarming.ui.addactivities.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddActivitiesViewModel : ViewModel() {
    private var _gardenList = MutableLiveData(listOf<String>("شماره 11", "شماره 22", "باغ پسته"))
    val gardenList : LiveData<List<String>> = _gardenList



}