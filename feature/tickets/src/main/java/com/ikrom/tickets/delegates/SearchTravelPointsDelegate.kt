package com.ikrom.tickets.delegates

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemSearchTravelPointsCardBinding

data class SearchTravelPointItem(
    val origin: String,
    val destination: String,
    val onDestinationChange: (String) -> Unit,
    val onClear: () -> Unit
): AdapterItem()

class SearchTravelPointsDelegate: BaseDelegateAdapter
<SearchTravelPointItem, SearchTravelPointsDelegate.SearchTravelPointsViewHolder>(
    SearchTravelPointItem::class.java) {

    inner class SearchTravelPointsViewHolder(val binding: ItemSearchTravelPointsCardBinding):
        DelegateViewHolder<SearchTravelPointItem>(binding){

            override fun bind(item: SearchTravelPointItem) {
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
        val binding = ItemSearchTravelPointsCardBinding.inflate(inflater, parent, false)
        return SearchTravelPointsViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_search_travel_points_card
    }
}