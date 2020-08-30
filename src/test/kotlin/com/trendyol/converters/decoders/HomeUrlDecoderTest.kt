package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.services.SectionService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class HomeUrlDecoderTest {

    private val sectionService = mockk<SectionService>()

    @Test
    fun `decode to default home page`() {
        val deeplink = DeepLink("ty://?Page=Home")
        val decoder = HomeUrlDecoder(sectionService)

        val condition = decoder.predicate(deeplink)
        val result = decoder.decode(deeplink)

        assertEquals(true, condition)
        assertEquals(WebUrl("https://www.trendyol.com"), result)
    }

    @Test
    fun `decode to default home page with section param`() {
        val deeplink = DeepLink("ty://?Page=Home&SectionId=1")

        every { sectionService.getSectionName(1) } returns "erkek"

        val decoder = HomeUrlDecoder(sectionService)

        val condition = decoder.predicate(deeplink)
        val result = decoder.decode(deeplink)

        assertEquals(true, condition)
        assertEquals(WebUrl("https://www.trendyol.com/butik/liste/erkek"), result)
    }

}