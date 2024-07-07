package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.example.utils.CyrilFilter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTravelPointsBinding

data class TravelPointsItem(
    val defaultText: String,
    val onOriginChange: (String) -> Unit,
    val onDestinationClick: () -> Unit,
): AdapterItem()

class TravelPointsDelegate: BaseDelegateAdapter<TravelPointsItem, TravelPointsDelegate.TravelPointsViewHolder>(
    TravelPointsItem::class.java) {
    inner class TravelPointsViewHolder(val binding: ItemTravelPointsBinding): DelegateViewHolder<TravelPointsItem>(binding){
        override fun bind(item: TravelPointsItem) {
            binding.originText.setText(item.defaultText)

            binding.originText.filters = arrayOf(CyrilFilter())
            binding.originText.doOnTextChanged {text, _, _, _ ->
                item.onOriginChange(text.toString())
            }
            binding.destinationText.setOnClickListener {
                item.onDestinationClick()
            }
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