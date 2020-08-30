package com.trendyol.converters.encoders

import muhas.converters.encoders.DefaultUrlEncoder
import muhas.model.DeepLink
import muhas.model.WebUrl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DefaultUrlEncoderTest {

    @Test
    fun `encode to default home page`() {
        val webUrl = WebUrl("http://trendyol.com/some-url")

        val condition = DefaultUrlEncoder.predicate(webUrl)
        val result = DefaultUrlEncoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Home"), result)
    }

}