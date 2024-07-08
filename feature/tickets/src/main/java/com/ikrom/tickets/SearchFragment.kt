package com.ikrom.tickets

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.R
import com.example.ui.adapters.CompositeAdapter
import com.example.ui.adapters.extensions.layoutConfigure
import com.example.ui.adapters.extensions.setBackgroundTint
import com.example.ui.adapters.extensions.setFullHeight
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ikrom.tickets.databinding.FragmentSearchBinding
import com.ikrom.tickets.delegates.SearchButtonsDelegate
import com.ikrom.tickets.delegates.SearchButtonsItem
import com.ikrom.tickets.delegates.SearchTravelPointItem
import com.ikrom.tickets.delegates.SearchTravelPointsDelegate

class SearchFragment : BottomSheetDialogFragment() {

    private val compositeAdapter = CompositeAdapter.Builder()
        .add(SearchTravelPointsDelegate())
        .add(SearchButtonsDelegate())
        .build()

    lateinit var binding: FragmentSearchBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        layoutConfigure(dialog) {bottomSheet ->
            bottomSheet?.setFullHeight()
            bottomSheet?.setBackgroundTint(requireContext(), R.color.bottom_sheet_color)
        }
        return dialog
    }

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
        compositeAdapter.add(
            SearchTravelPointItem(
                "Москва",
                "",
                {}
            )
        )
        compositeAdapter.add(SearchButtonsItem({}, {}, {}, {}))
    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = compositeAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupBinding(): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}