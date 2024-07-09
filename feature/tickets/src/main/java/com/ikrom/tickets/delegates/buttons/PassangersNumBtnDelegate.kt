package com.ikrom.tickets.delegates.buttons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapters.AdapterItem
import com.example.ui.adapters.DelegateAdapter
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ItemBtnPassendersNumBinding

data class PassengersNumBtnItem(
    val passengersNum: Int,
    val passengersClass: String
): AdapterItem()

class PassengersNumBtnDelegate: DelegateAdapter
<PassengersNumBtnItem, PassengersNumBtnDelegate.PassengersNumBtnVH>(PassengersNumBtnItem::class.java) {

    inner class PassengersNumBtnVH(val binding: ItemBtnPassendersNumBinding):
        DelegateVH<PassengersNumBtnItem>(binding){

        override fun bind(item: PassengersNumBtnItem) {
            binding.passengerText.text = "${item.passengersNum},${item.passengersClass}"
        }

    }

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ItemBtnPassendersNumBinding.inflate(inflater, parent, false)
        return PassengersNumBtnVH(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_btn_passenders_num
    }
}