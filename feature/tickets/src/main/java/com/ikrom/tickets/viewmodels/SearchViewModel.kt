package com.ikrom.tickets.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {
    private val _destinationText = MutableLiveData("")
    val destinationLiveData: LiveData<String> = _destinationText
    var destinationField = ""

    fun setDestination(text: String) {
        _destinationText.postValue(text)
    }

    fun onDestinationTyped(text: String){
        destinationField = text
    }
}