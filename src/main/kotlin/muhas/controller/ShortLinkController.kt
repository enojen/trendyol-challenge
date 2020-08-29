package muhas.controller

import muhas.DeepLinkRequest
import muhas.ShortLink
import muhas.repository.redis.CounterRepo
import muhas.repository.mysql.ShortLinkRepo
import muhas.services.ShortLinkService
import muhas.services.UrlConverterService
import muhas.util.BaseConversion
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URL


@RestController
class ShortLinkController(val urlConverterService: UrlConverterService,
                          val shortLinkRepo: ShortLinkRepo,
                          val shortLiService: ShortLinkService,
                          val counterRepo: CounterRepo
                          ) {


    @PostMapping("/shortlink/deeplink")
    fun fromDeepLink(@RequestBody a: DeepLinkRequest): Any {

        val shortLinkId = counterRepo.getId()

        val shorLinkHash = BaseConversion.encode(shortLinkId)
        val webUrl = urlConverterService.toUrl(a).url
        val deepLink = a.url

        val shortLink = ShortLink(
                shortLink = shorLinkHash,
                deepLink = deepLink,
                webUrl = webUrl
        )

        val result = shortLinkRepo.save(shortLink)
        return result
    }

    @PostMapping("/shortlink/url")
    fun fromUrl(@RequestBody a: DeepLinkRequest): Any {
        val shortLinkId = counterRepo.getId()

        val shorLinkHash = BaseConversion.encode(shortLinkId)
        val deepLink = urlConverterService.toDeepLink(a).deepLink.url
        val webUrl = a.url

        val shortLink = ShortLink(
                shortLink = shorLinkHash,
                deepLink = deepLink,
                webUrl = webUrl
        )

        val result = shortLinkRepo.save(shortLink)
        return result
    }

    @GetMapping("/shortlink/details")
    fun details(@RequestBody a: DeepLinkRequest): Any {

        val hash = URL(a.url).path.split("/")[1]

        val shortLink = shortLiService.findById(hash)

        return shortLink.map {
            ShortLinkResponse(it.webUrl, it.deepLink)
        }.orElseGet {
            ShortLinkResponse(error = "Not found", deepLink = "", webUrl = "")
        }
    }

    data class ShortLinkResponse(val webUrl: String, val deepLink: String, val error: String? = null)
}
