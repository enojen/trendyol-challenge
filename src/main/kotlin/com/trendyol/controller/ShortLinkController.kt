package com.trendyol.controller

import com.trendyol.DeepLinkRequest
import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.services.ShortLinkService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URL


@RestController
class ShortLinkController(val shortLinkService: ShortLinkService) {

    @PostMapping("/shortlink/deeplink")
    fun fromDeepLink(@RequestBody a: DeepLinkRequest): Any {
        return shortLinkService.createShortLink(DeepLink(a.url))
    }

    @PostMapping("/shortlink/url")
    fun fromWebUrl(@RequestBody a: DeepLinkRequest): Any {
        return shortLinkService.createShortLink(WebUrl(a.url))
    }

    @GetMapping("/shortlink/details")
    fun details(@RequestBody a: DeepLinkRequest): Any {

        val hash = URL(a.url).path.split("/")[1]

        val shortLink = shortLinkService.findById(hash)

        return shortLink.map {
            ShortLinkResponse(it.webUrl, it.deepLink)
        }.orElseGet {
            ShortLinkResponse(error = "Not found", deepLink = "", webUrl = "")
        }
    }

    data class ShortLinkResponse(val webUrl: String, val deepLink: String, val error: String? = null)
}
