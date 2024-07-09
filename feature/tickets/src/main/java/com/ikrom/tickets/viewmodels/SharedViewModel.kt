package com.ikrom.tickets.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var originText = ""
    val destinationLiveData = MutableLiveData("")
}