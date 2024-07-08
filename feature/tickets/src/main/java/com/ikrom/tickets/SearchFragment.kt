package com.ikrom.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.adapters.CompositeAdapter
import com.ikrom.tickets.databinding.FragmentSearchBinding
import com.ikrom.tickets.delegates.SearchButtonsDelegate
import com.ikrom.tickets.delegates.SearchButtonsItem
import com.ikrom.tickets.delegates.SearchTravelPointItem
import com.ikrom.tickets.delegates.SearchTravelPointsDelegate

class SearchFragment : Fragment() {

    private val compositeAdapter: CompositeAdapter = CompositeAdapter.Builder()
        .add(SearchTravelPointsDelegate())
        .add(SearchButtonsDelegate())
        .build()
    private val viewModel: SearchViewModel by viewModels()
    lateinit var binding: FragmentSearchBinding

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
            SearchTravelPointItem("", "", {}),
            SearchButtonsItem({
                findNavController().navigate(R.id.to_empty_fragment)
            }, {
                viewModel.setDestination("Куда угодно")
            }, {
                findNavController().navigate(R.id.to_empty_fragment)
            }, {
                findNavController().navigate(R.id.to_empty_fragment)
            })
        ))
        updateAdapterData()
    }

    private fun updateAdapterData(){
        viewModel.destinationText.observe(viewLifecycleOwner) {text ->
            compositeAdapter.updateItem(0,
                SearchTravelPointItem(
                    "Москва",
                    text,
                    {}
                )
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
        super.onStop()
        compositeAdapter.clear()
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}