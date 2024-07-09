package com.ikrom.tickets.delegates.travelpoints_delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTripFilledBinding

data class TripFilledItem(
    val origin: String,
    val destination: String,
    override val onClick: () -> Unit
): AdapterItem()

class TripFilledDelegate: DelegateAdapter
<TripFilledItem, TripFilledDelegate.ViewHolder>(
    TripFilledItem::class.java) {

    inner class ViewHolder(val binding: ItemTripFilledBinding):
        DelegateVH<TripFilledItem>(binding){

            override fun bind(item: TripFilledItem) {
                binding.originText.text = item.origin
                binding.destinationText.text = item.destination
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTripFilledBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_trip_filled
    }
}