package com.ikrom.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {
    private val _destinationText = MutableLiveData("")
    val destinationText: LiveData<String> = _destinationText

    fun setDestination(text: String) {
        _destinationText.value = text
    }
}