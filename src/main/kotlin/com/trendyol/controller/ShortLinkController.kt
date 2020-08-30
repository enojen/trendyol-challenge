package com.trendyol.controller

import com.trendyol.ShortLinkNotFoundException
import com.trendyol.model.Link
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.services.ShortLinkService
import com.trendyol.web.Result
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/shortlink")
class ShortLinkController(val shortLinkService: ShortLinkService) : BaseController() {

    @Value("\${shortlink.domain}")
    val shortLinkDomain: String = "localhost/%s"

    @PostMapping("/details")
    fun details(@RequestBody link: Link): Any {
        val shortLink = shortLinkService.findShortLink(link)
        return Result.Success()
                .add("link", shortLink.link)
                .add("deepLink", shortLink.deepLink)
                .add("shortLink", shortLinkDomain.format(shortLink.shortLink))
                .build()
    }

    @PostMapping("/from-deeplink")
    fun fromDeepLink(@RequestBody deepLink: TyDeepLink): Any {
        val shortLink = shortLinkService.createFromDeepLink(deepLink)
        return Result.Success()
                .add("shortLink", shortLinkDomain.format(shortLink.shortLink))
                .build()
    }

    @PostMapping("/from-link")
    fun fromLink(@RequestBody link: TyLink): Any {
        val shortLink = shortLinkService.createFromLink(link)
        return Result.Success()
                .add("shortLink", shortLinkDomain.format(shortLink.shortLink))
                .build()
    }
}
