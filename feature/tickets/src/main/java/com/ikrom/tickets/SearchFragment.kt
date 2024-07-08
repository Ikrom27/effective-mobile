package com.ikrom.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.R
import com.example.ui.adapters.CompositeAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ikrom.tickets.databinding.FragmentSearchBinding
import com.ikrom.tickets.delegates.SearchTravelPointItem
import com.ikrom.tickets.delegates.SearchTravelPointsDelegate

class SearchFragment : BottomSheetDialogFragment() {

    private val compositeAdapter = CompositeAdapter.Builder()
        .add(SearchTravelPointsDelegate())
        .build()

    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setupBinding()
        setupRecyclerView()
        return view
    }

    override fun onStart() {
        super.onStart()
        setupAdapterData()
    }

    private fun setupAdapterData(){
        compositeAdapter.add(
            SearchTravelPointItem(
                "Москва",
                "",
                {}
            )
        )
    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = compositeAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupBinding(): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setStyle(style: Int, theme: Int) {
        super.setStyle(R.style.BottomSheetStyle, theme)
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}