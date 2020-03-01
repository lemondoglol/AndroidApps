package com.example.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel :ViewModel() {
    /**
     * Data will be observed
     * */
    val currentInput = MutableLiveData<String>()
}