package com.trendyol.converters.encoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
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