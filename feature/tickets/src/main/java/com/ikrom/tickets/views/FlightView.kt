package com.ikrom.tickets.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.utils.PriceUtils
import com.ikrom.tickets.R
import com.ikrom.tickets.databinding.ViewFlyightBinding

class FlightView: FrameLayout {
    private var binding: ViewFlyightBinding

    var airline: String = ""
        set(value) {
            binding.airline.text = value
            field = value
        }

    var flightTimes: List<String> = emptyList()
        set(value) {
            binding.flightTimes.text = value.joinToString("  ")
            field = value
        }

    var price: Int = 0
        set(value) {
            binding.price.text = PriceUtils.format(value) + " ₽ "
            field = value
        }

    var color: Int = 0
        set(value) {
            binding.airlineImage.background.setTint(value)
            field = value
        }

    var hideSeparator: Boolean = false
        set(value) {
            if(value){
                binding.separator.visibility = View.INVISIBLE
            } else {
                binding.separator.visibility = View.VISIBLE
            }
            Log.d("MyView", "Separator visibility: ${binding.separator.visibility}")
            field = value
        }

    init {
        inflate(context, R.layout.view_flyight, this)
        binding = ViewFlyightBinding.inflate(LayoutInflater.from(context), this, true)
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
        binding = ViewFlyightBinding.inflate(LayoutInflater.from(context), this, true)
    }
}