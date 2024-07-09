package com.ikrom.tickets.delegates.travelpoints_delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTravelPointsFilledBinding

data class TravelPointFilledItem(
    val origin: String,
    val destination: String,
    override val onClick: () -> Unit
): AdapterItem()

class TravelPointsFilledDelegate: DelegateAdapter
<TravelPointFilledItem, TravelPointsFilledDelegate.ViewHolder>(
    TravelPointFilledItem::class.java) {

    inner class ViewHolder(val binding: ItemTravelPointsFilledBinding):
        DelegateVH<TravelPointFilledItem>(binding){

            override fun bind(item: TravelPointFilledItem) {
                binding.originText.text = item.origin
                binding.destinationText.text = item.destination
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTravelPointsFilledBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_travel_points_filled
    }
}