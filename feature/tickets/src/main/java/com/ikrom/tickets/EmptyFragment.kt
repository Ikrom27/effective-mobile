package com.ikrom.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ikrom.tickets.databinding.FragmentEmptyBinding
import com.ikrom.tickets.databinding.FragmentSearchBinding

class EmptyFragment : Fragment() {

    lateinit var binding: FragmentEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setupBinding()
        setupButtons()
        return view
    }

    private fun setupButtons(){
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
    }

    private fun setupBinding(): View {
        binding = FragmentEmptyBinding.inflate(layoutInflater)
        return binding.root
    }
}