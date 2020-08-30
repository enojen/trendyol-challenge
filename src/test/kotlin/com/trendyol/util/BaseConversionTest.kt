package com.trendyol.util

import org.junit.Assert
import org.junit.Test


class BaseConversionTest {

    @Test
    fun `check conversion`(){
        val hash = BaseConversion.encode(Long.MAX_VALUE)
        val id = BaseConversion.decode(hash)

        Assert.assertEquals(Long.MAX_VALUE, id)
    }

}