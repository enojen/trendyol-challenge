package com.trendyol.services

import com.trendyol.model.Link
import com.trendyol.model.ShortLink
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.repository.mysql.ShortLinkRepo
import com.trendyol.repository.redis.CounterRepo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException
import java.util.*


class ShortLinkServiceTest {

    private val link = TyLink("http://www.trendyol.com/butik/liste")
    private val deepLink = TyDeepLink("ty://?Page=Home")

    private val linkConverter = mockk<LinkConverter> {
        every { toDeepLink(link) } returns deepLink
        every { toLink(deepLink) } returns link
    }

    private val counterRepo = mockk<CounterRepo> {
        every { getId() } returns 1000
    }

    private val shortLinkRepo = mockk<ShortLinkRepo>()

    private val shortLinkService = ShortLinkService(linkConverter, shortLinkRepo, counterRepo)


    @Test
    fun `create shortlink from deep link`() {

        every { shortLinkRepo.save(any<ShortLink>()) } returns ShortLink("", "", "")

        val result = shortLinkService.createShortLink(deepLink)

        assertEquals("qi", result.shortLink)
        assertEquals(deepLink.url, result.deepLink)
        assertEquals(link.url, result.link)
    }


    @Test
    fun `create shortlink from link`() {

        every { shortLinkRepo.save(any<ShortLink>()) } returns ShortLink("", "", "")

        val result = shortLinkService.createShortLink(link)

        assertEquals("qi", result.shortLink)
        assertEquals(deepLink.url, result.deepLink)
        assertEquals(link.url, result.link)
    }

    @Test
    fun `returns shortlink if exists`() {

        every { shortLinkRepo.findById("abc") } returns Optional.of(ShortLink("abs", "l1", "d1"))

        val result = shortLinkService.findShortLink(Link("http://some-domain/abc"))

        assertEquals("abs", result.shortLink)
        assertEquals("l1", result.deepLink)
        assertEquals("d1", result.link)
    }

    @Test
    fun `throws exception if shortlink not found`() {
        every { shortLinkRepo.findById("abc") } returns Optional.empty()

        assertThrows<RuntimeException> {
            shortLinkService.findShortLink(Link("http://some-domain/abc"))
        }
    }

}