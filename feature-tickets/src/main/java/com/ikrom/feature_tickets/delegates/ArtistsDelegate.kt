package com.ikrom.feature_tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.ikrom.base_adapter.adapters.BaseAdapter
import com.ikrom.base_adapter.adapters.AdapterItem
import com.ikrom.feature_tickets.R
import com.ikrom.feature_tickets.databinding.ItemArtistBinding


data class ArtistItem(
    val artistName: String,
    val town: String,
    val price: String,
    val imageUrl: String
): AdapterItem()


class ArtistsDelegate: BaseAdapter<ArtistItem>() {

    inner class ArtistViewHolder(val binding: ItemArtistBinding): BaseViewHolder<ArtistItem>(binding){
        override fun bind(item: ArtistItem) {
            binding.artistName.text = item.artistName
            binding.artistImage.load(item.imageUrl)
            binding.town.text = item.town
            binding.price.text = item.price
        }
    }

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<ArtistItem> {
        val binding = ItemArtistBinding.inflate(inflater, parent, false)
        return ArtistViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_artist
    }
}