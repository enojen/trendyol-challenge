package com.trendyol.converters.encoders


import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.services.SectionService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HomerUrlEncoderTest {

    private val sectionService = mockk<SectionService>()

    @Test
    fun `encode to home page`() {
        val encoder = HomeUrlEncoder(sectionService)

        val webUrl = WebUrl("http://trendyol.com/")

        val condition = encoder.predicate(webUrl)
        val result = encoder.encode(webUrl)

        assertEquals(true, condition)
        assertEquals(DeepLink("ty://?Page=Home"), result)
    }

    @Test
    fun `encode to home page with non-empty path`() {
        val encoder = HomeUrlEncoder(sectionService)

        val webUrl = WebUrl("http://trendyol.com/butik/liste")

        val condition = encoder.predicate(webUrl)
        val result = encoder.encode(webUrl)

        assertEquals(true, condition)
        assertEquals(DeepLink("ty://?Page=Home"), result)
    }

    @Test
    fun `encode to home page with section id`() {

        every { sectionService.getSectionId("erkek") } returns 1

        val encoder = HomeUrlEncoder(sectionService)

        val webUrl = WebUrl("http://trendyol.com/butik/liste/erkek")

        val condition = encoder.predicate(webUrl)
        val result = encoder.encode(webUrl)

        assertEquals(true, condition)
        assertEquals(DeepLink("ty://?Page=Home&SectionId=1"), result)
    }

}

