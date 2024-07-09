package com.ikrom.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.adapters.CompositeAdapter
import com.ikrom.tickets.databinding.FragmentSearchBinding
import com.ikrom.tickets.delegates.Place
import com.ikrom.tickets.delegates.PlacesCardDelegate
import com.ikrom.tickets.delegates.PlacesItem
import com.ikrom.tickets.delegates.SearchButtonsDelegate
import com.ikrom.tickets.delegates.SearchButtonsItem
import com.ikrom.tickets.delegates.travelpoints_delegates.TripDestinationItem
import com.ikrom.tickets.delegates.travelpoints_delegates.TripDestinationDelegate
import com.ikrom.tickets.viewmodels.SearchViewModel
import com.ikrom.tickets.viewmodels.SharedViewModel

class SearchFragment : Fragment() {

    private val compositeAdapter: CompositeAdapter = CompositeAdapter.Builder()
        .add(TripDestinationDelegate())
        .add(SearchButtonsDelegate())
        .add(PlacesCardDelegate())
        .build()
    private val viewModel: SearchViewModel by viewModels()
    lateinit var binding: FragmentSearchBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setupBinding()
        setupRecyclerView()
        setupAdapterData()
        return view
    }

    private fun setupAdapterData(){
        compositeAdapter.setItems(listOf(
            TripDestinationItem("", "") {},
            SearchButtonsItem({
                findNavController().navigate(R.id.to_empty_fragment)
            }, {
                viewModel.setDestination("Куда угодно")
            }, {
                findNavController().navigate(R.id.to_empty_fragment)
            }, {
                findNavController().navigate(R.id.to_empty_fragment)
            }),
            PlacesItem(listOf(
                Place(
                    "Стамбул",
                    "Популярное направление",
                    "file:///android_asset/pl1.png"
                ),
                Place(
                    "Сочи",
                    "Популярное направление",
                    "file:///android_asset/pl2.png"
                ),
                Place(
                    "Пхукет",
                    "Популярное направление",
                    "file:///android_asset/pl3.png"
                )
            ))
        ))
        updateAdapterData()
    }

    private fun updateAdapterData(){
        viewModel.setDestination(sharedViewModel.destinationLiveData.value ?: "")
        viewModel.destinationLiveData.observe(viewLifecycleOwner) { text ->
            compositeAdapter.updateItem(0,
                TripDestinationItem(
                    sharedViewModel.originText,
                    text
                ) { input ->
                    viewModel.onDestinationTyped(input)
                }
            )
        }
    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = compositeAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupBinding(): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStop() {
        sharedViewModel.destinationLiveData.value = viewModel.destinationField
        compositeAdapter.clear()
        super.onStop()
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}