package com.ikrom.tickets.delegates.buttons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.BaseDelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemBtnPassendersNumBinding

data class PassengersNumBtnItem(
    val passengersNum: Int,
    val passengersClass: String
): AdapterItem()

class PassengersNumBtnDelegate: BaseDelegateAdapter
<PassengersNumBtnItem, PassengersNumBtnDelegate.PassengersNumBtnViewHolder>(PassengersNumBtnItem::class.java) {

    inner class PassengersNumBtnViewHolder(val binding: ItemBtnPassendersNumBinding):
        DelegateViewHolder<PassengersNumBtnItem>(binding){

        override fun bind(item: PassengersNumBtnItem) {
            binding.passengerText.text = "${item.passengersNum},${item.passengersClass}"
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemBtnPassendersNumBinding.inflate(inflater, parent, false)
        return PassengersNumBtnViewHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_btn_passenders_num
    }
}