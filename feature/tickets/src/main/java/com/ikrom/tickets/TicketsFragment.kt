package com.ikrom.tickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikrom.base_adapter.adapters.CompositeAdapter
import com.ikrom.tickets.databinding.FragmentTicketsBinding
import com.ikrom.tickets.delegates.ArtistsDelegate
import com.ikrom.tickets.delegates.HorizontalListDelegate
import com.ikrom.tickets.delegates.HorizontalListItem
import com.ikrom.tickets.delegates.TextAdapter
import com.ikrom.tickets.delegates.TextItem
import com.ikrom.tickets.delegates.TravelPointsDelegate
import com.ikrom.tickets.delegates.TravelPointsItem
import dagger.Lazy
import javax.inject.Inject

class TicketsFragment : Fragment() {

    @Inject
    internal lateinit var ticketsViewModelFactory: Lazy<TicketsViewModel.Factory>

    private val ticketsViewModel: TicketsViewModel by viewModels {
        ticketsViewModelFactory.get()
    }

    private lateinit var binding: FragmentTicketsBinding
    private val compositeAdapter = CompositeAdapter.Builder()
        .add(TextAdapter())
        .add(TravelPointsDelegate())
        .add(HorizontalListDelegate())
        .build()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<TicketsComponentViewModel>()
            .ticketsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = setupBinding()
        setupRecyclerView()
        ticketsViewModel.updateArtistList()
        return view
    }

    private fun setupBinding(): View {
        binding = FragmentTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = compositeAdapter
    }

    fun setupAdapterData(){
        if (compositeAdapter.itemCount == 0){
            compositeAdapter.addToPosition(0, TextItem("Поиск дешевых авиабилетов"))
            compositeAdapter.addToPosition(1, TravelPointsItem("", "", {}))
            ticketsViewModel.artistItem.observe(viewLifecycleOwner) { artists ->
                compositeAdapter.addToPosition(2, item = HorizontalListItem(
                    title = "С музакой",
                    adapter = ArtistsDelegate().apply { setItems(artists) }
                ))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterData()
    }
}