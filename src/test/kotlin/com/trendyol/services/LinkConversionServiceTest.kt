package com.trendyol.services

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.model.LinkConversion
import com.trendyol.repository.mysql.LinkConversionRepo
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LinkConversionServiceTest {

    private val link = TyLink("http://www.trendyol.com/butik/liste")
    private val deepLink = TyDeepLink("ty://?Page=Home")

    private val linkConverter = mockk<LinkConverter> {
        every { toDeepLink(link) } returns deepLink
        every { toLink(deepLink) } returns link
    }

    private val linkConversionRepo = mockk<LinkConversionRepo>()

    private val linkConversionService = LinkConversionService(linkConversionRepo, linkConverter)


    @Test
    fun `convert link and save request`() {
        val savedRequest = slot<LinkConversion>()

        every { linkConversionRepo.save(capture(savedRequest)) } returns LinkConversion(1, "", "")

        val result = linkConversionService.toDeepLink(link)

        assertEquals(deepLink, result)

        assertEquals(link.url, savedRequest.captured.source)
        assertEquals(deepLink.url, savedRequest.captured.target)
        assertEquals(0, savedRequest.captured.id)
    }

    @Test
    fun `convert deep link and save request`() {
        val savedConversion = slot<LinkConversion>()

        every { linkConversionRepo.save(capture(savedConversion)) } returns LinkConversion(1, "", "")

        val result = linkConversionService.toLink(deepLink)

        assertEquals(link, result)

        assertEquals(deepLink.url, savedConversion.captured.source)
        assertEquals(link.url, savedConversion.captured.target)
        assertEquals(0, savedConversion.captured.id)
    }
}