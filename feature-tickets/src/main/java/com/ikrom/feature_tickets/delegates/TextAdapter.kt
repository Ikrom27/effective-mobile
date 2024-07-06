package com.ikrom.feature_tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikrom.base_adapter.adapters.BaseDelegateAdapter
import com.ikrom.base_adapter.adapters.AdapterItem
import com.ikrom.feature_tickets.R
import com.ikrom.feature_tickets.databinding.ItemTextBinding

data class TextItem(
    val text: String
): AdapterItem()

class TextAdapter: BaseDelegateAdapter<TextItem, TextAdapter.TextViewHolder>(TextItem::class.java) {
    inner class TextViewHolder(val binding: ItemTextBinding): DelegateViewHolder<TextItem>(binding){
        override fun bind(item: TextItem) {
            binding.textView.text = item.text
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemTextBinding.inflate(inflater, parent, false)
        return TextViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_text
    }
}