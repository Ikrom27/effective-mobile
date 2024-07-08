package com.ikrom.tickets.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utils.PriceUtils
import com.ikrom.data.Repository
import com.ikrom.tickets.delegates.ArtistItem
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

    private lateinit var disposable: Disposable

    var originText: String = getLastOrigin()

    @SuppressLint("CheckResult")
    fun updateArtistList() {
        disposable = repository.getArtistsList()
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
            }, {})
    }

    fun getLastOrigin(): String {
        return repository.getSavedOrigin()
    }

    fun onOriginChange(text: String){
        originText = text
    }

    fun saveOrigin(){
        repository.saveOrigin(originText)
    }

    override fun onCleared() {
        super.onCleared()
        if (this::disposable.isInitialized){
            disposable.dispose()
        }
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


