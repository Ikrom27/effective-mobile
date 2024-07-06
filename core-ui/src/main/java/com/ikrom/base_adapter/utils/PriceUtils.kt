package com.ikrom.base_adapter.utils

object PriceUtils {
    fun format(price: Int): String{
        var sourcePrice = price.toString()
        var newPrice = ArrayList<Char>()

        var c = 0
        for(i in sourcePrice.length - 1 downTo 0){
            if (c == 3){
                newPrice.add(' ')
                c = 0
            }
            newPrice.add(sourcePrice[i])
            c++
        }

        return newPrice.reversed().joinToString("")
    }
}