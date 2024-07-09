package com.ikrom.tickets.delegates.buttons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemBtnReturnFlightBinding

data class ReturnFlightBtnItem(
    val label: String
): AdapterItem()

class ReturnFlightBtnDelegate: DelegateAdapter
<ReturnFlightBtnItem, ReturnFlightBtnDelegate.ReturnFlightBtnVH>(ReturnFlightBtnItem::class.java) {

    inner class ReturnFlightBtnVH(val binding: ItemBtnReturnFlightBinding):
        DelegateVH<ReturnFlightBtnItem>(binding){

        override fun bind(item: ReturnFlightBtnItem) {
            binding.label.text = item.label
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemBtnReturnFlightBinding.inflate(inflater, parent, false)
        return ReturnFlightBtnVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_btn_return_flight
    }
}