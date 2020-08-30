package com.trendyol

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtensionsTest {

    @Test
    fun `remove Turkish chars`(){
        val str = "ÖÇŞİĞÜIöçşiğüı"
        assertEquals("OCSIGUIocsigui", str.replaceTurkishChars())
    }

}