package com.ikrom.effective_mobile.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ikrom.base_adapter.BaseDelegateAdapter
import com.ikrom.base_adapter.model.AdapterItem
import com.ikrom.effective_mobile.R
import com.ikrom.effective_mobile.databinding.ItemTravelPointsBinding

data class TravelPointsItem(
    val origin: String,
    val destination: String,
    override val onClick: (() -> Unit)
): AdapterItem()

class TravelPointsDelegate: BaseDelegateAdapter<TravelPointsItem, TravelPointsDelegate.TravelPointsViewHolder>(TravelPointsItem::class.java) {
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