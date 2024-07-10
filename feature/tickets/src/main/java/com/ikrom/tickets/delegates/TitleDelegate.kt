package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTitleBinding

data class TitleItem(
    val title: String
): AdapterItem()

class TitleDelegate: DelegateAdapter<TitleItem, TitleDelegate.ViewHolder>(TitleItem::class.java) {
    inner class ViewHolder(val binding: ItemTitleBinding): DelegateVH<TitleItem>(binding) {
        override fun bind(item: TitleItem) {
            binding.title.text = item.title
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTitleBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_title
    }
}