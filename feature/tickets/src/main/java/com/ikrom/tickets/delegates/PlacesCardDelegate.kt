package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemPlaceCardBinding
import com.ikrom.tickets.views.PlaceView

data class PlacesItem(
    val places: List<Place>
): AdapterItem()

data class Place(
    val label: String,
    val description: String,
    val image: String
)

class PlacesCardDelegate: BaseDelegateAdapter
<PlacesItem, PlacesCardDelegate.RestPlacesViewHolder>(
    PlacesItem::class.java
) {
    inner class RestPlacesViewHolder(val binding: ItemPlaceCardBinding):
        DelegateViewHolder<PlacesItem>(binding){

        override fun bind(item: PlacesItem){
            for (place in item.places){
                val placeView = PlaceView(binding.root.context).apply {
                    label = place.label
                    description = place.description
                    imageUrl = place.image
                }
                binding.root.addView(placeView)
            }
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemPlaceCardBinding.inflate(inflater, parent, false)
        return RestPlacesViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_place_card
    }
}