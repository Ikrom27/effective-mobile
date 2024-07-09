package com.ikrom.tickets.delegates.buttons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemBtnDateBinding

data class DateBtnItem(
    val dateNum: Int,
    val month: String,
    val weekDay: String
): AdapterItem()

class DateBtnDelegate: DelegateAdapter
<DateBtnItem, DateBtnDelegate.DateBtnVH>(DateBtnItem::class.java) {

    inner class DateBtnVH(val binding: ItemBtnDateBinding):
        DelegateVH<DateBtnItem>(binding){

        override fun bind(item: DateBtnItem) {
            binding.lightPart.text = "${item.dateNum} ${item.month}"
            binding.darkPart.text = ", ${item.weekDay}"
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemBtnDateBinding.inflate(inflater, parent, false)
        return DateBtnVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_btn_date
    }
}