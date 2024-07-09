package com.ikrom.tickets.delegates.travelpoints_delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.example.utils.CyrilFilter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTripOriginBinding

data class TripOriginItem(
    val defaultText: String,
    val onOriginChange: (String) -> Unit,
    val onDestinationClick: () -> Unit,
): AdapterItem()

class TripOriginDelegate: DelegateAdapter<TripOriginItem, TripOriginDelegate.ViewHolder>(
    TripOriginItem::class.java) {
    inner class ViewHolder(val binding: ItemTripOriginBinding): DelegateVH<TripOriginItem>(binding){
        override fun bind(item: TripOriginItem) {
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
        val binding = ItemTripOriginBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_trip_origin
    }
}