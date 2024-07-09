package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemNotifyBinding

data class NotifyItem(
    val label: String,
    val onNotifyChange: (Boolean) -> Unit
): AdapterItem()

class NotifyDelegate: DelegateAdapter
<NotifyItem, NotifyDelegate.NotifyVH>(NotifyItem::class.java) {

    inner class NotifyVH(val binding: ItemNotifyBinding): DelegateVH<NotifyItem>(binding) {
        override fun bind(item: NotifyItem) {
            binding.label.text = item.label

        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemNotifyBinding.inflate(inflater, parent, false)
        return NotifyVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_notify
    }


}