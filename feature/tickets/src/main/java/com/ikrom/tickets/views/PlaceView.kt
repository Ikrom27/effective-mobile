package com.ikrom.tickets.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import coil.load
import com.ikrom.tickets.databinding.ViewPlaceBinding

class PlaceView: FrameLayout {
    private lateinit var binding: ViewPlaceBinding

    var label: String = ""
        set(value) {
            binding.label.text = value
            field = value
        }

    var description: String = ""
        set(value) {
            binding.description.text = value
            field = value
        }

    var imageUrl: String = ""
        set(value) {
            binding.placeImage.load(value)
            field = value
        }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        binding = ViewPlaceBinding.inflate(LayoutInflater.from(context), this, true)
    }
}