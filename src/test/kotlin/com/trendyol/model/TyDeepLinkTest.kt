package com.trendyol.model


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TyDeepLinkTest {

    @Test
    fun `parse path and query params`() {
        val link = TyDeepLink("ty://?Page=Home&")

        assertEquals(listOf(Pair("Page", "Home")), link.params)
        assertEquals("", link.path)
        assertEquals(0, link.pathSegments.size)
    }

    @Test
    fun `throw exception for invalid url`() {
        assertThrows<IllegalArgumentException> {
            TyLink("http://google.com")
        }

        assertThrows<IllegalArgumentException> {
            TyLink("ftp://www.trendyol.com")
        }
    }
}