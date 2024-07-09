package com.ikrom.tickets.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utils.PriceUtils
import com.ikrom.data.Repository
import com.ikrom.tickets.delegates.ArtistItem
import com.ikrom.tickets.delegates.Flight
import com.ikrom.tickets.delegates.FlightsItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider


class TicketsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _artistList = MutableLiveData<List<ArtistItem>>()
    val artistItem: LiveData<List<ArtistItem>> = _artistList

    private val _flightsItem = MutableLiveData<List<Flight>>()
    val flightsItem: LiveData<List<Flight>> = _flightsItem

    private val disposables = ArrayList<Disposable>()

    var originText: String = repository.getSavedOrigin()

    fun updateArtistList() {
        disposables.add(
            repository.getArtistsList()
            .subscribeOn(Schedulers.io())
            .map {
                it.offers.map { artistResponse ->
                    ArtistItem(
                        artistName = artistResponse.title,
                        town = artistResponse.town,
                        price = PriceUtils.format(artistResponse.price.value),
                        imageUrl = "file:///android_asset/${artistResponse.id}.png"
                    )
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _artistList.postValue(it)
            }, {

            })
        )
    }

    fun updateFlightsList(){
        disposables.add(
            repository.getFlightsList()
                .subscribeOn(Schedulers.io())
                .map {
                    it.tickets_offers.map { flightsResponse ->
                        Flight(
                            airline = flightsResponse.airline,
                            times = flightsResponse.timeRange,
                            price = flightsResponse.price.value
                        )
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _flightsItem.postValue(it)
                }, {})
        )
    }

    fun onOriginChange(text: String){
        originText = text
    }

    fun saveOrigin(){
        repository.saveOrigin(originText)
    }

    override fun onCleared() {
        for(disposable in disposables){
            disposable.dispose()
        }
        super.onCleared()
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


