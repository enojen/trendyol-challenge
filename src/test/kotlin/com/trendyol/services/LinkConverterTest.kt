package com.trendyol.services

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinkConverterTest {

    private val sectionService = mockk<SectionService>()

    @Test
    fun `home link to deep link`() {
        val converter = LinkConverter(sectionService)

        every { sectionService.getSectionId("erkek") } returns 1

        val link = TyLink("https://www.trendyol.com/butik/liste/erkek")
        val expected = TyDeepLink("ty://?Page=Home&SectionId=1")

        val result = converter.toDeepLink(link)
        assertEquals(expected, result)
    }

    @Test
    fun `product link to deep link`() {
        val converter = LinkConverter(sectionService)

        val link = TyLink("https://www.trendyol.com/brand/product-p-123")
        val expected = TyDeepLink("ty://?Page=Product&ContentId=123")

        val result = converter.toDeepLink(link)
        assertEquals(expected, result)
    }

    @Test
    fun `search link to deep link`() {
        val converter = LinkConverter(sectionService)

        val link = TyLink("https://www.trendyol.com/tum--urunler?q=ŞIĞ")
        val expected = TyDeepLink("ty://?Page=Search&Query=ŞIĞ")

        val result = converter.toDeepLink(link)
        assertEquals(expected, result)
    }

    @Test
    fun `other link to deep link`() {
        val converter = LinkConverter(sectionService)

        val link = TyLink("https://www.trendyol.com/something-else")
        val expected = TyDeepLink("ty://?Page=Home")

        val result = converter.toDeepLink(link)
        assertEquals(expected, result)
    }


    @Test
    fun `home deep link to link`() {
        val converter = LinkConverter(sectionService)

        every { sectionService.getSectionName(1) } returns "erkek"

        val deepLink = TyDeepLink("ty://?Page=Home&SectionId=1")
        val expected = TyLink("https://www.trendyol.com/butik/liste/erkek")

        val result = converter.toLink(deepLink)
        assertEquals(expected, result)
    }

    @Test
    fun `product deep link to link`() {
        val converter = LinkConverter(sectionService)

        val deepLink = TyDeepLink("ty://?Page=Product&ContentId=123")
        val expected = TyLink("https://www.trendyol.com/brand/name-p-123")

        val result = converter.toLink(deepLink)
        assertEquals(expected, result)
    }

    @Test
    fun `search deep link to link`() {
        val converter = LinkConverter(sectionService)

        val deepLink = TyDeepLink("ty://?Page=Search&Query=ASD")
        val expected = TyLink("https://www.trendyol.com/tum--urunler?q=ASD")

        val result = converter.toLink(deepLink)
        assertEquals(expected, result)
    }

    @Test
    fun `other deep link to link`() {
        val converter = LinkConverter(sectionService)

        val deepLink = TyDeepLink("ty://?Page=Favorites")
        val expected = TyLink("https://www.trendyol.com")

        val result = converter.toLink(deepLink)
        assertEquals(expected, result)
    }
}