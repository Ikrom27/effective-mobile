package com.ikrom.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ikrom.tickets.databinding.FragmentAllTicketsBinding
import com.ikrom.tickets.databinding.FragmentTicketsBinding


class AllTicketsFragment : Fragment() {

    private lateinit var binding: FragmentAllTicketsBinding

    private lateinit var origin: String
    private lateinit var destination: String
    private lateinit var attributes: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setupBinding()
        init()
        setupToolbar()
        return view
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
}