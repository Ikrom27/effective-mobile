package com.example.ui.adapters.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.clearDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}