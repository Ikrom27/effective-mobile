package com.ikrom.tickets.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utils.extensions.toLocalDateTime
import com.ikrom.data.Repository
import com.ikrom.tickets.delegates.TicketItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Provider

class AllTicketsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _ticketsLiveData = MutableLiveData<List<TicketItem>>()
    val ticketsLiveData: LiveData<List<TicketItem>> = _ticketsLiveData

    private val disposable = ArrayList<Disposable>()

    init {
        updateTickets()
    }

    fun updateTickets(){
        disposable.add(
            repository.getAllTickets()
                .subscribeOn(Schedulers.io())
                .map {
                    it.tickets.map {ticket ->
                        TicketItem(
                            badge = ticket.badge,
                            price = ticket.price.value,
                            departureDate = ticket.departure.date.toLocalDateTime(),
                            departureAirport = ticket.departure.airport,
                            arrivalDate = ticket.arrival.date.toLocalDateTime(),
                            arrivalAirport = ticket.arrival.airport,
                            hasTransfer = ticket.hasTransfer
                        )
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _ticketsLiveData.postValue(it)
                }, {
                    Log.e("AllTicketsViewModel", it.message ?: "none")
                })
        )
    }

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