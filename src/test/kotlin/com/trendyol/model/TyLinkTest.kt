package com.trendyol.model


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TyLinkTest {

    @Test
    fun `parse path and query params`() {
        val link = TyLink("http://trendyol.com/butik/liste?param1=p1")

        assertEquals(link.params, listOf(Pair("param1", "p1")))
        assertEquals(link.path, "/butik/liste")
        assertEquals(link.pathSegments.size, 2)
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