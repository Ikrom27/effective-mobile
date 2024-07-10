package com.ikrom.tickets.delegates.buttons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemBtnDateBinding
import com.ikrom.tickets.databinding.ItemBtnFilterBinding

data class FilterBtnItem(
    override val onClick: (() -> Unit)
): AdapterItem()

class FilterBtnDelegate: DelegateAdapter
<FilterBtnItem, FilterBtnDelegate.ViewHolder>(FilterBtnItem::class.java) {

    inner class ViewHolder(val binding: ItemBtnFilterBinding):
        DelegateVH<FilterBtnItem>(binding){

        override fun bind(item: FilterBtnItem) {

        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemBtnFilterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_btn_filter
    }
}