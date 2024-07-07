package com.example.utils.extensions

import android.annotation.SuppressLint
import android.content.Context


@SuppressLint("InlinedApi")
fun Int.dpToPx(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    val density = displayMetrics.density
    return (this * density + 0.5f).toInt()
}