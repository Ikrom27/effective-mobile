package com.ikrom.tickets

import android.content.ClipData.Item
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.ui.adapters.CompositeAdapter
import com.example.ui.adapters.extensions.clearDecorations
import com.example.ui.adapters.item_decorations.MarginItemDecoration
import com.example.utils.extensions.dpToPx
import com.ikrom.tickets.databinding.FragmentTicketsBinding
import com.ikrom.tickets.delegates.ArtistsDelegate
import com.ikrom.tickets.delegates.travelpoints_delegates.TripFilledItem
import com.ikrom.tickets.delegates.travelpoints_delegates.TripFilledDelegate
import com.ikrom.tickets.delegates.FlightsDelegate
import com.ikrom.tickets.delegates.FlightsItem
import com.ikrom.tickets.delegates.HorizontalListDelegate
import com.ikrom.tickets.delegates.HorizontalListItem
import com.ikrom.tickets.delegates.NotifyDelegate
import com.ikrom.tickets.delegates.NotifyItem
import com.ikrom.tickets.delegates.TextAdapter
import com.ikrom.tickets.delegates.TextItem
import com.ikrom.tickets.delegates.TitleDelegate
import com.ikrom.tickets.delegates.TitleItem
import com.ikrom.tickets.delegates.travelpoints_delegates.TripOriginDelegate
import com.ikrom.tickets.delegates.travelpoints_delegates.TripOriginItem
import com.ikrom.tickets.delegates.WideButtonDelegate
import com.ikrom.tickets.delegates.WideButtonItem
import com.ikrom.tickets.delegates.buttons.DateBtnDelegate
import com.ikrom.tickets.delegates.buttons.DateBtnItem
import com.ikrom.tickets.delegates.buttons.FilterBtnDelegate
import com.ikrom.tickets.delegates.buttons.FilterBtnItem
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
    private lateinit var ticketsItemDecoration: ItemDecoration
    private lateinit var defaultItemDecoration: ItemDecoration
    private lateinit var artistsItemDecoration: ItemDecoration
    private lateinit var buttonsItemDecoration: ItemDecoration

    private lateinit var binding: FragmentTicketsBinding
    private val compositeAdapter = CompositeAdapter.Builder()
        .add(TextAdapter())
        .add(TripOriginDelegate())
        .add(HorizontalListDelegate())
        .add(FlightsDelegate())
        .add(TripFilledDelegate())
        .add(WideButtonDelegate())
        .add(NotifyDelegate())
        .add(TitleDelegate())
        .build()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = setupBinding()
        initValues()
        setupRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterData()
    }

    private fun inject(){
        ViewModelProvider(this).get<TicketsComponentViewModel>()
            .ticketsComponent.inject(this)
    }

    private fun initValues(){
        ticketsItemDecoration = MarginItemDecoration(
            startSpace = 47.dpToPx(requireContext()),
            endSpace = 110.dpToPx(requireContext()),
            betweenSpace = 13.dpToPx(requireContext()),
        )
        defaultItemDecoration = MarginItemDecoration(
            startSpace = 28.dpToPx(requireContext()),
            endSpace = 28.dpToPx(requireContext()),
            betweenSpace = 19.dpToPx(requireContext())
        )
        val margin = resources.getDimension(com.example.ui.R.dimen.main_horizontal_margin)
        artistsItemDecoration = MarginItemDecoration(
            startSpace = margin.toInt(),
            endSpace = margin.toInt(),
            betweenSpace = 67.dpToPx(requireContext()),
            true
        )

        buttonsItemDecoration = MarginItemDecoration(
            startSpace = margin.toInt(),
            endSpace = margin.toInt(),
            betweenSpace = margin.toInt() / 2,
            true
        )
    }

    private fun setupBinding(): View {
        binding = FragmentTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = compositeAdapter
    }

    private fun setupAdapterData(){
        sharedViewModel.destinationLiveData.observe(viewLifecycleOwner){
            if(it.isBlank()){
                setDefaultItems()
            } else {
                setTicketsItems()
            }
        }
    }

    private fun setTicketsItems() {
        ticketsViewModel.updateFlightsList()
        compositeAdapter.setItems(listOf(
            TripFilledItem(
                ticketsViewModel.originText,
                sharedViewModel.destinationLiveData.value ?: ""
            ) { showDialog() },
            getButtonsList(),
            FlightsItem(listOf()),
            WideButtonItem(
                "Посмотреть все билеты"
            ) {
                val bundle = Bundle().apply {
                    putString("origin", ticketsViewModel.originText)
                    putString("destination", sharedViewModel.destinationLiveData.value)
                    putString("attributes", "${DATE}, ${PASSENGERS}")
                }
                findNavController().navigate(
                    R.id.action_ticketsFragment_to_allTicketsFragment,
                    bundle
                )
            },
            NotifyItem(
                "Подписка на цену", {}
            )
        ))
        ticketsViewModel.flightsItem.observe(viewLifecycleOwner){
            compositeAdapter.updateItem(2,
                FlightsItem(it.take(3))
            )
        }
        binding.recyclerView.clearDecorations()
        binding.recyclerView.addItemDecoration(ticketsItemDecoration)
    }

    private fun setDefaultItems(){
        ticketsViewModel.updateArtistList()
        compositeAdapter.setItems(listOf(
            TextItem("Поиск дешевых авиабилетов"),
            TripOriginItem(
                originText = ticketsViewModel.originText,
                destinationText = sharedViewModel.destinationLiveData.value ?: "",
                onOriginChange = { ticketsViewModel.onOriginChange(it) },
                onDestinationClick = {
                    sharedViewModel.originText = ticketsViewModel.originText
                    showDialog()
                }
            ),
            TitleItem("Музыкально отлетать"),
            HorizontalListItem(
                adapter = ArtistsDelegate().apply { setItems(emptyList()) },
                itemDecoration = artistsItemDecoration
            )
        ))
        ticketsViewModel.artistItem.observe(viewLifecycleOwner){
            compositeAdapter.updateItem(3,
                HorizontalListItem(
                    adapter = ArtistsDelegate().apply { setItems(it) },
                    itemDecoration = artistsItemDecoration,
                )
            )
        }
        binding.recyclerView.clearDecorations()
        binding.recyclerView.addItemDecoration(defaultItemDecoration)
    }

    private fun showDialog(){
        val fragment = ModalFragment()
        fragment.show(parentFragmentManager, ModalFragment.TAG)
    }


    private fun getButtonsList(): HorizontalListItem {
        return HorizontalListItem(
            adapter = CompositeAdapter.Builder()
                .add(DateBtnDelegate())
                .add(PassengersNumBtnDelegate())
                .add(ReturnFlightBtnDelegate())
                .add(FilterBtnDelegate())
                .build().apply {
                    add(ReturnFlightBtnItem("обратно"))
                    add(DateBtnItem(24, "фев", "суб"))
                    add(PassengersNumBtnItem(1, "эконом"))
                    add(FilterBtnItem({}))
                },
            itemDecoration = buttonsItemDecoration
        )
    }

    companion object {
        const val DATE = "23 Февраля"
        const val PASSENGERS = "1 пассажир"
    }

    override fun onPause() {
        ticketsViewModel.saveOrigin()
        super.onPause()
    }
}