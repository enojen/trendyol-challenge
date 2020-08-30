package com.trendyol.converters.encoders


import io.mockk.every
import io.mockk.mockk
import muhas.converters.encoders.HomeUrlEncoder
import muhas.model.DeepLink
import muhas.model.WebUrl
import muhas.services.SectionService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class HomerUrlEncoderTest {

    private val sectionService = mockk<SectionService>()

    @Test
    fun `encode to home page`() {
        val encoder = HomeUrlEncoder(sectionService)

        val webUrl = WebUrl("http://trendyol.com/")

        val condition = encoder.predicate(webUrl)
        val result = encoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Home"), result)
    }

    @Test
    fun `encode to home page with non-empty path`() {
        val encoder = HomeUrlEncoder(sectionService)

        val webUrl = WebUrl("http://trendyol.com/butik/liste")

        val condition = encoder.predicate(webUrl)
        val result = encoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Home"), result)
    }

    @Test
    fun `encode to home page with section id`() {

        every { sectionService.getSectionId("erkek") } returns 1

        val encoder = HomeUrlEncoder(sectionService)

        val webUrl = WebUrl("http://trendyol.com/butik/liste/erkek")

        val condition = encoder.predicate(webUrl)
        val result = encoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Home&SectionId=1"), result)
    }

}

