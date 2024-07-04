package com.ikrom.base_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ikrom.base_adapter.model.AdapterItem


abstract class BaseDelegateAdapter<T : AdapterItem, in VH: BaseDelegateAdapter.DelegateViewHolder<T>>(
    private val classType: Class<out T>
) {
    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder

    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return createViewHolder(inflater, parent)
    }

    abstract fun getLayoutId(): Int

    abstract class DelegateViewHolder<T: AdapterItem>(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){
        abstract fun bind(item: T)
    }



    fun onBindViewHolder(item: T, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as DelegateViewHolder<T>).bind(item)
    }

    fun isForViewType(item: AdapterItem): Boolean {
        return item.javaClass == classType
    }
}