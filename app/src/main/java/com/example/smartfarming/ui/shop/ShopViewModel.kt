package com.example.smartfarming.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopViewModel : ViewModel() {
    private  var _text = MutableLiveData<String>().apply {
        value = "This is shopping fargment"
    }

    var text : LiveData<String> = _text;

}