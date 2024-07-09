package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.DelegateAdapter
import com.example.ui.adapters.AdapterItem
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemTextBinding

data class TextItem(
    val text: String
): AdapterItem()

class TextAdapter: DelegateAdapter<TextItem, TextAdapter.TextVH>(TextItem::class.java) {
    inner class TextVH(val binding: ItemTextBinding): DelegateVH<TextItem>(binding){
        override fun bind(item: TextItem) {
            binding.textView.text = item.text
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTextBinding.inflate(inflater, parent, false)
        return TextVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_text
    }
}