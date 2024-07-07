package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.ui.adapters.BaseAdapter
import com.example.ui.adapters.BaseDelegateAdapter
import com.example.ui.adapters.AdapterItem
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemHorizontalListBinding


data class HorizontalListItem(
    val adapter: Adapter<*>,
    val itemDecoration: ItemDecoration? = null
): AdapterItem()


class HorizontalListDelegate:
    BaseDelegateAdapter<HorizontalListItem, HorizontalListDelegate.HorizontalListViewHolder>
        (HorizontalListItem::class.java) {
    inner class HorizontalListViewHolder(val binding: ItemHorizontalListBinding):
        DelegateViewHolder<HorizontalListItem>(binding){
        override fun bind(item: HorizontalListItem) {
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
                .apply { orientation = LinearLayoutManager.HORIZONTAL }
            binding.recyclerView.adapter = item.adapter
            item.itemDecoration?.let { decoration ->
                if (binding.recyclerView.itemDecorationCount == 0){
                    binding.recyclerView.addItemDecoration(decoration)
                }
            }
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