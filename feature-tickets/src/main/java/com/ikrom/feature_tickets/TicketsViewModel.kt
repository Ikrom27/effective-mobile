package com.ikrom.feature_tickets

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ikrom.data.Repository
import javax.inject.Inject
import javax.inject.Provider


class TicketsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    @SuppressLint("CheckResult")
    fun fetchArtistList() {

    }

    class Factory @Inject constructor(
        private val repository: Provider<Repository>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == TicketsViewModel::class.java)
            return TicketsViewModel(repository.get()) as T
        }
    }
}


