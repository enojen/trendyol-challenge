package com.trendyol.converters.encoders

import muhas.converters.encoders.ProductUrlEncoder
import muhas.converters.encoders.SearchUrlEncoder
import muhas.model.DeepLink
import muhas.model.WebUrl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class SearchUrlEncoderTest {

    @Test
    fun `encode to search deeplink without param`() {
        val webUrl = WebUrl("http://trendyol.com/tum--urunler")

        val condition = SearchUrlEncoder.predicate(webUrl)
        val result = SearchUrlEncoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Search"), result)
    }

    @Test
    fun `encode to search deeplink with param`() {
        val webUrl = WebUrl("http://trendyol.com/tum--urunler?q=ĞİŞ")

        val condition = SearchUrlEncoder.predicate(webUrl)
        val result = SearchUrlEncoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Search&Query=ĞİŞ"), result)
    }
}