package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class SearchUrlDecoderTest {

    @Test
    fun `decode to product page`() {
        val deeplink = DeepLink("ty://?Page=Search&Query=123")

        val condition = SearchUrlDecoder.predicate(deeplink)
        val result = SearchUrlDecoder.decode(deeplink)

        assertEquals(true, condition)
        assertEquals(WebUrl("https://www.trendyol.com/tum--urunler?q=123"), result)
    }
}