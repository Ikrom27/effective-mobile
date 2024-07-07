package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikrom.base_adapter.adapters.BaseDelegateAdapter
import com.ikrom.base_adapter.adapters.AdapterItem
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTravelPointsBinding

data class TravelPointsItem(
    val origin: String,
    val destination: String,
    override val onClick: (() -> Unit)
): AdapterItem()

class TravelPointsDelegate: BaseDelegateAdapter<TravelPointsItem, TravelPointsDelegate.TravelPointsViewHolder>(
    TravelPointsItem::class.java) {
    inner class TravelPointsViewHolder(binding: ItemTravelPointsBinding): DelegateViewHolder<TravelPointsItem>(binding){
        override fun bind(item: TravelPointsItem) {

        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTravelPointsBinding.inflate(inflater, parent, false)
        return TravelPointsViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_travel_points
    }
}