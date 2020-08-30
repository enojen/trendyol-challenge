package muhas.services

import muhas.model.DeepLink
import muhas.model.ShortLink
import muhas.model.WebUrl
import muhas.repository.mysql.ShortLinkRepo
import muhas.repository.redis.CounterRepo
import muhas.util.BaseConversion
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*


@Service
class ShortLinkService(val urlConverterService: UrlConverterService,
                       val shortLinkRepo: ShortLinkRepo,
                       val counterRepo: CounterRepo) {

    fun createShortLink(webUrl: WebUrl): ShortLink {
        val shortLinkHash = generateHash()
        val deepLink = urlConverterService.toDeepLink(webUrl)

        val shortLink = ShortLink(
                shortLink = shortLinkHash,
                deepLink = deepLink.url,
                webUrl = webUrl.url
        )

        return shortLinkRepo.save(shortLink)
    }

    fun createShortLink(deepLink: DeepLink): ShortLink {
        val shortLinkHash = generateHash()
        val webUrl = urlConverterService.toWebUrl(deepLink)

        val shortLink = ShortLink(
                shortLink = shortLinkHash,
                deepLink = deepLink.url,
                webUrl = webUrl.url
        )

        return shortLinkRepo.save(shortLink)
    }


    @Cacheable(key = "#id", cacheNames = ["cache1"])
    fun findById(id: String): Optional<ShortLink> {
        Thread.sleep(5000)
        return shortLinkRepo.findById(id)
    }

    private fun generateHash(): String {
        val shortLinkId = counterRepo.getId()
        return BaseConversion.encode(shortLinkId)
    }

}