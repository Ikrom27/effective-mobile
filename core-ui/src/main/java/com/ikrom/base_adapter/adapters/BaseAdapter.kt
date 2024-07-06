package com.ikrom.base_adapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T: AdapterItem>:
    BaseAdapterHandler<T, BaseAdapter.BaseViewHolder<T>>(){

    abstract fun getLayoutId(): Int
    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<T>

    abstract class BaseViewHolder<T>(binding: ViewBinding): ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        return createViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(mItems[position])
        super.onBindViewHolder(holder, position)
    }
}