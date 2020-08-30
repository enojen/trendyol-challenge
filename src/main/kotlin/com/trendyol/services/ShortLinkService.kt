package com.trendyol.services

import com.trendyol.ShortLinkNotFoundException
import com.trendyol.model.Link
import com.trendyol.model.TyDeepLink
import com.trendyol.model.ShortLink
import com.trendyol.model.TyLink
import com.trendyol.repository.mysql.ShortLinkRepo
import com.trendyol.repository.redis.CounterRepo
import com.trendyol.util.BaseConversion
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.lang.RuntimeException


@Service
class ShortLinkService(val linkConverterService: LinkConverter,
                       val shortLinkRepo: ShortLinkRepo,
                       val counterRepo: CounterRepo) {

    fun createFromLink(link: TyLink): ShortLink {
        val deepLink = linkConverterService.toDeepLink(link)
        return createShortLink(link = link, deepLink = deepLink)
    }

    fun createFromDeepLink(deepLink: TyDeepLink): ShortLink {
        val link = linkConverterService.toLink(deepLink)
        return createShortLink(link = link, deepLink = deepLink)
    }

    @Cacheable(key = "#link.url", cacheNames = ["short-link-cache"])
    fun findShortLink(link: Link): ShortLink {
        val hash = link.pathSegments.last()
        val shortLink = shortLinkRepo.findById(hash).orElseThrow { ShortLinkNotFoundException() }
        return shortLink
    }

    private fun generateHash(): String {
        val shortLinkId = counterRepo.getId()
        return BaseConversion.encode(shortLinkId)
    }

    private fun createShortLink(link: TyLink, deepLink: TyDeepLink): ShortLink {
        val hash = generateHash()
        val shortLink = ShortLink(
                shortLink = hash,
                deepLink = deepLink.url,
                link = link.url
        )

        shortLinkRepo.save(shortLink)
        return shortLink
    }

}