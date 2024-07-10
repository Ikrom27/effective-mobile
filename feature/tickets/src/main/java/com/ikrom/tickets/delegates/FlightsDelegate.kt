package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemFlightsCardBinding
import com.ikrom.tickets.views.FlightView

data class FlightsItem(
    val flights: List<Flight>
): AdapterItem()

data class Flight(
    val airline: String,
    val times: List<String>,
    val price: Int
)

class FlightsDelegate: DelegateAdapter<FlightsItem, FlightsDelegate.FlightsVH>(
    FlightsItem::class.java
) {
    inner class FlightsVH(val binding: ItemFlightsCardBinding): DelegateVH<FlightsItem>(binding){
        private val colors = listOf(
            ContextCompat.getColor(binding.root.context, com.example.ui.R.color.red),
            ContextCompat.getColor(binding.root.context, com.example.ui.R.color.blue),
            ContextCompat.getColor(binding.root.context, com.example.ui.R.color.white)
        )

        override fun bind(item: FlightsItem) {
            for(i in 0 until 3){
                if(item.flights.size <= i){
                    break
                }
                val flightView = FlightView(binding.root.context).apply {
                    airline = item.flights[i].airline
                    flightTimes = item.flights[i].times
                    price = item.flights[i].price
                    color = colors[i]
                    hideSeparator = i == 2
                }
                binding.root.addView(flightView, i+1)
            }
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemFlightsCardBinding.inflate(inflater, parent, false)
        return FlightsVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_flights_card
    }
}