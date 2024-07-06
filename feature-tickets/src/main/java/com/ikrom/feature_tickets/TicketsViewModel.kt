package com.ikrom.feature_tickets

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ikrom.data.Repository
import com.ikrom.feature_tickets.delegates.ArtistItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider


class TicketsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _artistList = MutableLiveData<List<ArtistItem>>()
    val artistItem: LiveData<List<ArtistItem>> = _artistList

    @SuppressLint("CheckResult")
    fun updateArtistList() {
        repository.getArtistsList()
            .subscribeOn(Schedulers.io())
            .map {
                it.offers.map { artistResponse ->
                    ArtistItem(
                        artistName = artistResponse.title,
                        city = artistResponse.town,
                        price = artistResponse.price.value
                    )
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _artistList.postValue(it)
            }, {})
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


