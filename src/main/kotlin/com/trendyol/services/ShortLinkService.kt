package com.trendyol.services

import com.trendyol.model.TyDeepLink
import com.trendyol.model.ShortLink
import com.trendyol.model.TyLink
import com.trendyol.repository.mysql.ShortLinkRepo
import com.trendyol.repository.redis.CounterRepo
import com.trendyol.util.BaseConversion
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*


@Service
class ShortLinkService(val linkConverterService: LinkConverterService,
                       val shortLinkRepo: ShortLinkRepo,
                       val counterRepo: CounterRepo) {

    fun createShortLink(link: TyLink): ShortLink {
        val shortLinkHash = generateHash()
        val deepLink = linkConverterService.toDeepLink(link)

        val shortLink = ShortLink(
                shortLink = shortLinkHash,
                deepLink = deepLink.url,
                link = link.url
        )

        return shortLinkRepo.save(shortLink)
    }

    fun createShortLink(deepLink: TyDeepLink): ShortLink {
        val shortLinkHash = generateHash()
        val link = linkConverterService.toLink(deepLink)

        val shortLink = ShortLink(
                shortLink = shortLinkHash,
                deepLink = deepLink.url,
                link = link.url
        )

        return shortLinkRepo.save(shortLink)
    }


    @Cacheable(key = "#id", cacheNames = ["cache1"])
    fun findById(id: String): Optional<ShortLink> {
        return shortLinkRepo.findById(id)
    }

    private fun generateHash(): String {
        val shortLinkId = counterRepo.getId()
        return BaseConversion.encode(shortLinkId)
    }

}