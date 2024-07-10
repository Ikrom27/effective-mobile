package com.ikrom.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.adapters.CompositeAdapter
import com.ikrom.tickets.databinding.FragmentAllTicketsBinding
import com.ikrom.tickets.databinding.FragmentTicketsBinding
import com.ikrom.tickets.delegates.TicketDelegate
import com.ikrom.tickets.di.AllTicketsComponentViewModel
import com.ikrom.tickets.di.TicketsComponentViewModel
import com.ikrom.tickets.viewmodels.AllTicketsViewModel
import com.ikrom.tickets.viewmodels.TicketsViewModel
import dagger.Lazy
import javax.inject.Inject


class AllTicketsFragment : Fragment() {

    private lateinit var binding: FragmentAllTicketsBinding

    private lateinit var origin: String
    private lateinit var destination: String
    private lateinit var attributes: String

    @Inject
    internal lateinit var allTicketsViewModelFactory: Lazy<AllTicketsViewModel.Factory>
    private val viewModel: AllTicketsViewModel by viewModels {
        allTicketsViewModelFactory.get()
    }

    private val compositeAdapter = CompositeAdapter.Builder()
        .add(TicketDelegate())
        .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setupBinding()
        inject()
        init()
        setupToolbar()
        setupRecyclerView()
        setupAdapterData()
        return view
    }

    private fun inject(){
        ViewModelProvider(this).get<AllTicketsComponentViewModel>()
            .allTicketsComponent.inject(this)
    }

    private fun init(){
        getArgs()
    }

    private fun getArgs(){
        arguments?.let {
            origin = it.getString("origin", "")
            destination = it.getString("destination", "")
            attributes = it.getString("attributes", "")
        }
    }

    private fun setupBinding(): View {
        binding = FragmentAllTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupToolbar(){
        binding.title.text = "${origin}-${destination}"
        binding.subtitle.text = attributes
    }

    private fun setupRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = compositeAdapter
    }

    private fun setupAdapterData(){
        viewModel.ticketsLiveData.observe(viewLifecycleOwner) {
            compositeAdapter.setItems(it)
        }
    }
}