package com.trendyol.converters.decoders

import io.mockk.every
import io.mockk.mockk
import muhas.converters.decoders.DefaultUrlDecoder
import muhas.converters.decoders.HomeUrlDecoder
import muhas.converters.encoders.DefaultUrlEncoder
import muhas.model.DeepLink
import muhas.model.WebUrl
import muhas.services.SectionService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class HomeUrlDecoderTest {

    private val sectionService = mockk<SectionService>()

    @Test
    fun `decode to default home page`() {
        val deeplink = DeepLink("ty://?Page=Home")
        val decoder = HomeUrlDecoder(sectionService)

        val condition = decoder.predicate(deeplink)
        val result = decoder.decode(deeplink)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(WebUrl("https://www.trendyol.com"), result)
    }

    @Test
    fun `decode to default home page with section param`() {
        val deeplink = DeepLink("ty://?Page=Home&SectionId=1")

        every { sectionService.getSectionName(1) } returns "erkek"

        val decoder = HomeUrlDecoder(sectionService)

        val condition = decoder.predicate(deeplink)
        val result = decoder.decode(deeplink)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(WebUrl("https://www.trendyol.com/butik/liste/erkek"), result)
    }

}