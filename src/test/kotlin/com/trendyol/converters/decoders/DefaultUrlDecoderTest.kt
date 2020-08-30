package com.trendyol.converters.decoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class DefaultUrlDecoderTest {

    @Test
    fun `decode to default home page`() {
        val deepLink = TyDeepLink("ty://?Page=Favorites")

        val condition = DefaultUrlDecoder.predicate(deepLink)
        val result = DefaultUrlDecoder.decode(deepLink)

        assertEquals(true, condition)
        assertEquals(TyLink("https://www.trendyol.com"), result)
    }

}