package com.trendyol.converters.encoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class DefaultUrlEncoderTest {

    @Test
    fun `encode to default home page`() {
        val webUrl = TyLink("http://trendyol.com/some-url")

        val condition = DefaultUrlEncoder.predicate(webUrl)
        val result = DefaultUrlEncoder.encode(webUrl)

        assertEquals(true, condition)
        assertEquals(TyDeepLink("ty://?Page=Home"), result)
    }

}