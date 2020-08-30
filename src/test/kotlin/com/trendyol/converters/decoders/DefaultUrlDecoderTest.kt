package com.trendyol.converters.decoders

import muhas.converters.decoders.DefaultUrlDecoder
import muhas.converters.encoders.DefaultUrlEncoder
import muhas.model.DeepLink
import muhas.model.WebUrl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DefaultUrlDecoderTest {

    @Test
    fun `decode to default home page`() {
        val deeplink = DeepLink("ty://?Page=Favorites")

        val condition = DefaultUrlDecoder.predicate(deeplink)
        val result = DefaultUrlDecoder.decode(deeplink)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(WebUrl("https://www.trendyol.com"), result)
    }

}