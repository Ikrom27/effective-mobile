package com.ikrom.tickets.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ikrom.data.Repository
import javax.inject.Inject
import javax.inject.Provider

class AllTicketsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    class Factory @Inject constructor(
        private val repository: Provider<Repository>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == AllTicketsViewModel::class.java)
            return AllTicketsViewModel(repository.get()) as T
        }
    }
}