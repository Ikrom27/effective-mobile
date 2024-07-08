package com.example.ui.adapters.extensions

import android.content.Context
import android.content.res.Resources
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

fun FrameLayout.setBackgroundTint(context: Context, color: Int){
    background.setTint(ContextCompat.getColor(context, color))
}

fun FrameLayout.setFullHeight(){
    minimumHeight = Resources.getSystem().displayMetrics.heightPixels
}