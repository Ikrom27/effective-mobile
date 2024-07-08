package com.ikrom.tickets.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemSearchButtonsBinding

data class SearchButtonsItem(
    val onDifficultRouteClick: () -> Unit,
    val onAnyWhereClick: () -> Unit,
    val onWeekendsClick: () -> Unit,
    val onHotTicketsClick: () -> Unit
): AdapterItem()

class SearchButtonsDelegate: BaseDelegateAdapter
<SearchButtonsItem, SearchButtonsDelegate.SearchButtonsViewHolder>(SearchButtonsItem::class.java) {
    inner class SearchButtonsViewHolder(val binding: ItemSearchButtonsBinding): DelegateViewHolder<SearchButtonsItem>(binding){
        override fun bind(item: SearchButtonsItem) {
            binding.btnAnywhere.setOnClickListener{
                item.onAnyWhereClick()
            }
            binding.btnDifficultRoute.setOnClickListener{
                item.onDifficultRouteClick()
            }
            binding.btnWeekends.setOnClickListener{
                item.onWeekendsClick()
            }
            binding.btnHotTickets.setOnClickListener{
                item.onHotTicketsClick()
            }
        }
    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemSearchButtonsBinding.inflate(inflater, parent, false)
        return SearchButtonsViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_search_buttons
    }
}