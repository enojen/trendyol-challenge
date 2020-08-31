package com.trendyol.converters.decoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SearchUrlDecoderTest {

    @Test
    fun `decode to search page`() {
        val deepLink = TyDeepLink("ty://?Page=Search&Query=123")

        val condition = SearchUrlDecoder.predicate(deepLink)
        val result = SearchUrlDecoder.decode(deepLink)

        assertEquals(true, condition)
        assertEquals(TyLink("https://www.trendyol.com/tum--urunler?q=123"), result)
    }

    @Test
    fun `not decode to search page if query param not exist`() {
        val deepLink = TyDeepLink("ty://?Page=Search")

        val condition = SearchUrlDecoder.predicate(deepLink)

        assertEquals(false, condition)
    }
}