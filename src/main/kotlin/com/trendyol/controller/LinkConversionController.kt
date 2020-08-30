package com.trendyol.controller

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.services.LinkConversionService
import com.trendyol.web.Result
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/convert")
class LinkConversionController(val linkConversionService: LinkConversionService): BaseController() {

    @PostMapping("/link-to-deeplink")
    fun toDeeplink(@RequestBody link: TyLink): Any {
        val deepLink = linkConversionService.toDeepLink(link)
        return Result.Success()
                .add("deepLink", deepLink.url)
                .build()
    }

    @PostMapping("/deeplink-to-link")
    fun toLink(@RequestBody deepLink: TyDeepLink): Any {
        val link = linkConversionService.toLink(deepLink)
        return Result.Success()
                .add("link", link.url)
                .build()
    }
}
