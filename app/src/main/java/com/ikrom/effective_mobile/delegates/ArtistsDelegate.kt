package com.ikrom.effective_mobile.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ikrom.base_adapter.BaseAdapter
import com.ikrom.base_adapter.model.AdapterItem
import com.ikrom.effective_mobile.R
import com.ikrom.effective_mobile.databinding.ItemArtistBinding

data class ArtistItem(
    val artistName: String,
    val city: String,
    val price: Int
): AdapterItem()


class ArtistsDelegate: BaseAdapter<ArtistItem>() {

    inner class ArtistViewHolder(binding: ItemArtistBinding): BaseViewHolder<ArtistItem>(binding){
        override fun bind(item: ArtistItem) {

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