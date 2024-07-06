package com.ikrom.base_adapter

import com.ikrom.base_adapter.utils.PriceUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsTest {
    @Test
    fun priceUtilsTest() {
        val price = 1555999
        val newPrice = PriceUtils.format(price)
        assertEquals("1 555 999", newPrice)
    }
}