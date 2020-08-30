package com.trendyol.controller

import com.trendyol.DeepLinkRequest
import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.model.web.ShortLinkResponse
import com.trendyol.services.ShortLinkService
import org.springframework.web.bind.annotation.*
import java.net.URL


@RestController
@RequestMapping("/api/shortlink")
class ShortLinkController(val shortLinkService: ShortLinkService) {

    @GetMapping
    fun details(@RequestBody a: DeepLinkRequest): Any {

        val hash = URL(a.url).path.split("/")[1]

        val shortLink = shortLinkService.findById(hash)

        return shortLink.map {
            ShortLinkResponse(it.webUrl, it.deepLink)
        }.orElseGet {
            ShortLinkResponse(error = "Not found", deepLink = "", webUrl = "")
        }
    }

    @PostMapping("/from-deeplink")
    fun fromDeepLink(@RequestBody a: DeepLinkRequest): Any {
        return shortLinkService.createShortLink(DeepLink(a.url))
    }

    @PostMapping("/from-weburl")
    fun fromWebUrl(@RequestBody a: DeepLinkRequest): Any {
        return shortLinkService.createShortLink(WebUrl(a.url))
    }
}
