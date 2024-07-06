package com.ikrom.feature_tickets

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import dagger.Lazy
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TicketsFragment : Fragment() {

    @Inject
    internal lateinit var ticketsViewModelFactory: Lazy<TicketsViewModel.Factory>

    private val ticketsViewModel: TicketsViewModel by viewModels {
        ticketsViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<TicketsComponentViewModel>()
            .ticketsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()
        ticketsViewModel.repository.getArtistsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ artistApiResponse ->
                val a = artistApiResponse.offers.size
                val b = a
            }, { throwable ->
                // Handle error
            })
    }
}