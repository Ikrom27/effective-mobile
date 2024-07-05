package com.ikrom.effective_mobile.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikrom.base_adapter.BaseDelegateAdapter
import com.ikrom.base_adapter.model.AdapterItem
import com.ikrom.effective_mobile.R
import com.ikrom.effective_mobile.databinding.ItemHorizontalListBinding


data class HorizontalListItem(
    val title: String,
    val nestedItems: List<Any>,
    val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
): AdapterItem()


class HorizontalListDelegate:
    BaseDelegateAdapter<HorizontalListItem, HorizontalListDelegate.HorizontalListViewHolder>
        (HorizontalListItem::class.java) {
    inner class HorizontalListViewHolder(val binding: ItemHorizontalListBinding):
        DelegateViewHolder<HorizontalListItem>(binding){
        override fun bind(item: HorizontalListItem) {
            binding.title.text = item.title
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
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