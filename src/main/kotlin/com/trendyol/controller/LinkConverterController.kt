package com.trendyol.controller

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.services.LinkConversionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/toLink")
class LinkConverterController(val linkConversionService: LinkConversionService) {

    @PostMapping("/link-to-deeplink")
    fun toDeeplink(@RequestBody link: TyLink): Any {
        return linkConversionService.toDeepLink(link)
    }

    @PostMapping("/deeplink-to-link")
    fun toLink(@RequestBody deepLink: TyDeepLink): Any {
        return linkConversionService.toLink(deepLink)
    }
}
