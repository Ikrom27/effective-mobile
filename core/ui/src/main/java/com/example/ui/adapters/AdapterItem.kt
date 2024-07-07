package com.example.ui.adapters

abstract class AdapterItem {
    open val onClick: (() -> Unit)? = null
    open val onLongClick: (() -> Unit)? = null
}