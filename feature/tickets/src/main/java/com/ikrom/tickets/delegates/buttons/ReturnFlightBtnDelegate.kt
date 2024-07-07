package com.ikrom.tickets.delegates.buttons

import android.graphics.drawable.Icon
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemBtnReturnFlightBinding

data class ReturnFlightBtnItem(
    val label: String
): AdapterItem()

class ReturnFlightBtnDelegate: BaseDelegateAdapter
<ReturnFlightBtnItem, ReturnFlightBtnDelegate.ReturnFlightBtnViewHolder>(ReturnFlightBtnItem::class.java) {

    inner class ReturnFlightBtnViewHolder(val binding: ItemBtnReturnFlightBinding):
        DelegateViewHolder<ReturnFlightBtnItem>(binding){

        override fun bind(item: ReturnFlightBtnItem) {
            binding.label.text = item.label
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemBtnReturnFlightBinding.inflate(inflater, parent, false)
        return ReturnFlightBtnViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_btn_return_flight
    }
}