package com.ikrom.effective_mobile.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikrom.base_adapter.CompositeAdapter
import com.ikrom.effective_mobile.R
import com.ikrom.effective_mobile.databinding.FragmentTicketsBinding
import com.ikrom.effective_mobile.delegates.TextAdapter
import com.ikrom.effective_mobile.delegates.TextItem

class TicketsFragment : Fragment() {
    private lateinit var binding: FragmentTicketsBinding
    private val compositeAdapter = CompositeAdapter.Builder()
        .add(TextAdapter())
        .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = setupBinding()
        setupRecyclerView()
        return view
    }

    private fun setupBinding(): View {
        binding = FragmentTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setupRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = compositeAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterData()
    }

    fun setupAdapterData(){
        compositeAdapter.addToEnd(TextItem("Поиск дешевых авиабилетов"))
    }
}