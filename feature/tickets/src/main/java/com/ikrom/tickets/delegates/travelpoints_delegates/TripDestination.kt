package com.ikrom.tickets.delegates.travelpoints_delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTripDestinationBinding

data class TripDestinationItem(
    val origin: String,
    val destination: String,
    val onDestinationChange: (String) -> Unit,
): AdapterItem()

class TripDestinationDelegate: DelegateAdapter
<TripDestinationItem, TripDestinationDelegate.ViewHolder>(
    TripDestinationItem::class.java) {

    inner class ViewHolder(val binding: ItemTripDestinationBinding):
        DelegateVH<TripDestinationItem>(binding){

            override fun bind(item: TripDestinationItem) {
                binding.originText.text = item.origin
                binding.icClear.setOnClickListener {
                    binding.destinationText.setText("")
                }
                binding.destinationText.setText(item.destination)
                binding.destinationText.doOnTextChanged {text, _, _, _ ->
                    item.onDestinationChange(text.toString())
                }
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTripDestinationBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_trip_destination
    }
}