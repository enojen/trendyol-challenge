package com.trendyol.converters.encoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SearchUrlEncoderTest {

    @Test
    fun `encode to search deeplink with param`() {
        val link = TyLink("http://trendyol.com/tum--urunler?q=ĞİŞ")

        val condition = SearchUrlEncoder.predicate(link)
        val result = SearchUrlEncoder.encode(link)

        assertEquals(true, condition)
        assertEquals(TyDeepLink("ty://?Page=Search&Query=ĞİŞ"), result)
    }

    @Test
    fun `do not encode to search deeplink without param`() {
        val link = TyLink("http://trendyol.com/tum--urunler")

        val condition = SearchUrlEncoder.predicate(link)

        assertEquals(false, condition)
    }
}