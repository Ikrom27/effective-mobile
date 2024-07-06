package com.ikrom.base_adapter.adapters

abstract class AdapterItem {
    open val onClick: (() -> Unit)? = null
    open val onLongClick: (() -> Unit)? = null
}