package com.ikrom.tickets.delegates.travelpoints_delegates

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTravelPointsDestinationBinding

data class TravelPointDestinationItem(
    val origin: String,
    val destination: String,
    val onDestinationChange: (String) -> Unit,
    val onClear: () -> Unit
): AdapterItem()

class TravelPointsDestinationDelegate: DelegateAdapter
<TravelPointDestinationItem, TravelPointsDestinationDelegate.ViewHolder>(
    TravelPointDestinationItem::class.java) {

    inner class ViewHolder(val binding: ItemTravelPointsDestinationBinding):
        DelegateVH<TravelPointDestinationItem>(binding){

            override fun bind(item: TravelPointDestinationItem) {
                binding.originText.text = item.origin
                binding.destinationText.setText(item.destination)
                binding.icClear.setOnClickListener {
                    binding.destinationText.setText("")
                }
                binding.destinationText.doOnTextChanged {text, _, _, _ ->
                    item.onDestinationChange(text.toString())
                    Log.d("adapter", text.toString())
                }
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTravelPointsDestinationBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_travel_points_destination
    }
}