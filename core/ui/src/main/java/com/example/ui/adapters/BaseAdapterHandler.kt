package com.example.ui.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R

abstract class BaseAdapterHandler<T: AdapterItem, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    protected val mItems = ArrayList<T>()

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        mItems[position].onClick?.let {unit->
            holder.itemView.setOnClickListener{unit()}
        }
        mItems[position].onLongClick?.let {unit ->
            holder.itemView.setOnLongClickListener {
                unit()
                true
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<T>){
        mItems.clear()
        mItems.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addToPosition(position: Int, item: T){
        if (itemCount <= position){
            mItems.add(item)
            notifyItemChanged(itemCount)
            return
        }
        mItems.add(position, item)
        notifyItemChanged(position)
    }

    fun updateItem(position: Int, item: T){
        if (position >= mItems.size){
            mItems.add(item)
            notifyItemChanged(itemCount)
            return
        }
        mItems[position] = item
        notifyItemChanged(position)
    }

    fun clear(){
        mItems.clear()
    }

    fun addToStart(item: T){
        addToPosition(0, item)
    }

    fun addItems(items: List<T>){
        val startPosition = mItems.size - 1
        mItems += items
        notifyItemRangeChanged(startPosition, mItems.size)
    }

    fun add(item: T){
        addToPosition(mItems.size, item)
    }
}