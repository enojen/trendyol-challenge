package com.trendyol.controller

import com.trendyol.model.Link
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink

import com.trendyol.model.web.ShortLinkResponse
import com.trendyol.services.ShortLinkService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/shortlink")
class ShortLinkController(val shortLinkService: ShortLinkService) {

    @GetMapping
    fun details(@RequestBody link: Link): Any {
        val shortLinkHash = link.pathSegments.last()
        val shortLink = shortLinkService.findById(shortLinkHash)

        return shortLink.map {
            ShortLinkResponse(it.link, it.deepLink)
        }.orElseGet {
            ShortLinkResponse(error = "Not found", deepLink = "", webUrl = "")
        }
    }

    @PostMapping("/from-deeplink")
    fun fromDeepLink(@RequestBody deepLink: TyDeepLink): Any {
        return shortLinkService.createShortLink(deepLink)
    }

    @PostMapping("/from-link")
    fun fromLink(@RequestBody link: TyLink): Any {
        return shortLinkService.createShortLink(link)
    }
}
