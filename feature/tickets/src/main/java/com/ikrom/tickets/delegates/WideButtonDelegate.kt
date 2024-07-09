package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemWideButtonBinding

data class WideButtonItem(
    val label: String,
    override val onClick: (() -> Unit)?
): AdapterItem()

class WideButtonDelegate: DelegateAdapter
<WideButtonItem, WideButtonDelegate.WideButtonVH>(WideButtonItem::class.java) {
    inner class WideButtonVH(val binding: ItemWideButtonBinding):
        DelegateVH<WideButtonItem>(binding){

        override fun bind(item: WideButtonItem) {
            binding.label.text = item.label
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemWideButtonBinding.inflate(inflater, parent, false)
        return WideButtonVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_wide_button
    }
}