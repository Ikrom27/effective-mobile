package com.example.utils

import android.text.InputFilter
import android.text.Spanned

class CyrilFilter: InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (source.toString().matches(Regex("[a-zA-Z]"))) {
            return ""
        }
        return source
    }

}