package com.ikrom.tickets.delegates.buttons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemBtnDateBinding

data class DateBtnItem(
    val dateNum: Int,
    val month: String,
    val weekDay: String
): AdapterItem()

class DateBtnDelegate: BaseDelegateAdapter
<DateBtnItem, DateBtnDelegate.DateBtnViewHolder>(DateBtnItem::class.java) {

    inner class DateBtnViewHolder(val binding: ItemBtnDateBinding):
        DelegateViewHolder<DateBtnItem>(binding){

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
        return DateBtnViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_btn_date
    }
}