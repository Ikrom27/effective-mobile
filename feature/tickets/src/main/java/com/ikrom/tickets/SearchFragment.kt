package com.ikrom.tickets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.R
import com.example.ui.adapters.CompositeAdapter
import com.example.ui.adapters.extensions.layoutConfigure
import com.example.ui.adapters.extensions.setBackgroundTint
import com.example.ui.adapters.extensions.setFullHeight
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
    private val viewModel: SearchViewModel by viewModels()
    lateinit var binding: FragmentSearchBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        layoutConfigure(dialog) {bottomSheet ->
            bottomSheet?.let {
                bottomSheet.setFullHeight()
                bottomSheet.setBackgroundTint(requireContext(), R.color.bottom_sheet_color)

            }
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
        compositeAdapter.addToPosition(0,
            SearchTravelPointItem("", "", {})
        )
        compositeAdapter.addToPosition(2, SearchButtonsItem({}, {
            viewModel.setDestination("Куда угодно")
        }, {}, {}))
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

    companion object {
        const val TAG = "SearchFragment"
    }
}