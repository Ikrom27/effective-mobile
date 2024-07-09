package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemFilledTravelPointsCardBinding

data class FilledTravelPointItem(
    val origin: String,
    val destination: String,
    override val onClick: () -> Unit
): AdapterItem()

class FilledTravelPointsDelegate: BaseDelegateAdapter
<FilledTravelPointItem, FilledTravelPointsDelegate.FilledTravelPointsViewHolder>(
    FilledTravelPointItem::class.java) {

    inner class FilledTravelPointsViewHolder(val binding: ItemFilledTravelPointsCardBinding):
        DelegateViewHolder<FilledTravelPointItem>(binding){

            override fun bind(item: FilledTravelPointItem) {
                binding.originText.text = item.origin
                binding.destinationText.text = item.destination
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemFilledTravelPointsCardBinding.inflate(inflater, parent, false)
        return FilledTravelPointsViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_travel_points_destination
    }
}