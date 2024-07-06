package com.ikrom.feature_tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikrom.base_adapter.adapters.BaseAdapter
import com.ikrom.base_adapter.adapters.BaseDelegateAdapter
import com.ikrom.base_adapter.adapters.AdapterItem
import com.ikrom.feature_tickets.R
import com.ikrom.feature_tickets.databinding.ItemHorizontalListBinding


data class HorizontalListItem(
    val title: String,
    val adapter: BaseAdapter<*>
): AdapterItem()


class HorizontalListDelegate:
    BaseDelegateAdapter<HorizontalListItem, HorizontalListDelegate.HorizontalListViewHolder>
        (HorizontalListItem::class.java) {
    inner class HorizontalListViewHolder(val binding: ItemHorizontalListBinding):
        DelegateViewHolder<HorizontalListItem>(binding){
        override fun bind(item: HorizontalListItem) {
            binding.title.text = item.title
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
                .apply { orientation = LinearLayoutManager.HORIZONTAL }
            binding.recyclerView.adapter = item.adapter
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemHorizontalListBinding.inflate(inflater, parent, false)
        return HorizontalListViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_horizontal_list
    }
}