package com.ikrom.tickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.adapters.CompositeAdapter
import com.example.ui.adapters.item_decorations.MarginItemDecoration
import com.ikrom.tickets.databinding.FragmentTicketsBinding
import com.ikrom.tickets.delegates.ArtistsDelegate
import com.ikrom.tickets.delegates.FilledTravelPointItem
import com.ikrom.tickets.delegates.FilledTravelPointsDelegate
import com.ikrom.tickets.delegates.Flight
import com.ikrom.tickets.delegates.FlightsDelegate
import com.ikrom.tickets.delegates.FlightsItem
import com.ikrom.tickets.delegates.HorizontalListDelegate
import com.ikrom.tickets.delegates.HorizontalListItem
import com.ikrom.tickets.delegates.TextAdapter
import com.ikrom.tickets.delegates.TextItem
import com.ikrom.tickets.delegates.TravelPointsDelegate
import com.ikrom.tickets.delegates.TravelPointsItem
import com.ikrom.tickets.delegates.buttons.DateBtnDelegate
import com.ikrom.tickets.delegates.buttons.DateBtnItem
import com.ikrom.tickets.delegates.buttons.PassengersNumBtnDelegate
import com.ikrom.tickets.delegates.buttons.PassengersNumBtnItem
import com.ikrom.tickets.delegates.buttons.ReturnFlightBtnDelegate
import com.ikrom.tickets.delegates.buttons.ReturnFlightBtnItem
import com.ikrom.tickets.di.TicketsComponentViewModel
import com.ikrom.tickets.viewmodels.SharedViewModel
import com.ikrom.tickets.viewmodels.TicketsViewModel
import dagger.Lazy
import javax.inject.Inject

class TicketsFragment : Fragment() {

    @Inject
    internal lateinit var ticketsViewModelFactory: Lazy<TicketsViewModel.Factory>

    private val ticketsViewModel: TicketsViewModel by viewModels {
        ticketsViewModelFactory.get()
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var binding: FragmentTicketsBinding
    private val compositeAdapter = CompositeAdapter.Builder()
        .add(TextAdapter())
        .add(TravelPointsDelegate())
        .add(HorizontalListDelegate())
        .add(FlightsDelegate())
        .add(FilledTravelPointsDelegate())
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
        sharedViewModel.destinationLiveData.observe(viewLifecycleOwner){
            if(it.isBlank()){
                setFirstEnterItems()
            } else {
                setTicketsItems()
            }
        }
    }

    private fun setTicketsItems() {
        compositeAdapter.setItems(listOf(
            FilledTravelPointItem(
                ticketsViewModel.originText,
                sharedViewModel.destinationLiveData.value ?: ""
            ) { showDialog() },
            getButtonsList(),
            FlightsItem(
                flights = listOf(
                    Flight("dfsdf", listOf("21:23", "21:23", "21:23", "21:23"), 231),
                    Flight("dfsdf", listOf("21:23", "21:23", "21:23", "21:23"), 231),
                    Flight("dfsdf", listOf("21:23", "21:23", "21:23", "21:23"), 231)
                )
            )
        ))
    }

    private fun setFirstEnterItems(){
        compositeAdapter.setItems(listOf(
            TextItem("Поиск дешевых авиабилетов"),
            TravelPointsItem(
                defaultText = ticketsViewModel.originText,
                onOriginChange = { ticketsViewModel.onOriginChange(it) },
                onDestinationClick = { showDialog() }
            ),
            HorizontalListItem(adapter = ArtistsDelegate().apply { setItems(emptyList()) })
        ))
    }

    private fun showDialog(){
        val fragment = ModalFragment()
        fragment.show(parentFragmentManager, ModalFragment.TAG)
    }


    private fun getButtonsList(): HorizontalListItem {
        val margin = resources.getDimension(com.example.ui.R.dimen.main_horizontal_margin)
        return HorizontalListItem(
            adapter = CompositeAdapter.Builder()
                .add(DateBtnDelegate())
                .add(PassengersNumBtnDelegate())
                .add(ReturnFlightBtnDelegate())
                .build().apply {
                    add(ReturnFlightBtnItem("обратно"))
                    add(DateBtnItem(24, "фев", "суб"))
                    add(PassengersNumBtnItem(1, "эконом"))
                },
            itemDecoration = MarginItemDecoration(
                startSpace = margin.toInt(),
                endSpace = margin.toInt(),
                betweenSpace = margin.toInt() / 2,
                isHorizontal = true
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterData()
    }

    override fun onStop() {
        ticketsViewModel.saveOrigin()
        super.onStop()
    }
}