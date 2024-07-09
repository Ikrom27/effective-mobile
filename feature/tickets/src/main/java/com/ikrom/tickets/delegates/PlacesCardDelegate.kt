package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemPlaceCardBinding
import com.ikrom.tickets.views.PlaceView

data class PlacesItem(
    val places: List<Place>
): AdapterItem()

data class Place(
    val label: String,
    val description: String,
    val image: String,
    val onClick: (String) -> Unit
)

class PlacesCardDelegate: DelegateAdapter
<PlacesItem, PlacesCardDelegate.RestPlacesVH>(
    PlacesItem::class.java
) {
    inner class RestPlacesVH(val binding: ItemPlaceCardBinding):
        DelegateVH<PlacesItem>(binding){

        override fun bind(item: PlacesItem){
            for (place in item.places){
                val placeView = PlaceView(binding.root.context).apply {
                    label = place.label
                    description = place.description
                    imageUrl = place.image
                    setOnClickListener {
                        place.onClick(place.label)
                    }
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
        return RestPlacesVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_place_card
    }
}