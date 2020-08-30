package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class DefaultUrlDecoderTest {

    @Test
    fun `decode to default home page`() {
        val deeplink = DeepLink("ty://?Page=Favorites")

        val condition = DefaultUrlDecoder.predicate(deeplink)
        val result = DefaultUrlDecoder.decode(deeplink)

        assertEquals(true, condition)
        assertEquals(WebUrl("https://www.trendyol.com"), result)
    }

}