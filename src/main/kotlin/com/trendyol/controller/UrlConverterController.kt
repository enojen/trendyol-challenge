package com.trendyol.controller

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.services.LinkConverterRequestService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/convert")
class UrlConverterController(val urlConverterRequestService: LinkConverterRequestService) {

    @PostMapping("/link-to-deeplink")
    fun toDeeplink(@RequestBody link: TyLink): Any {
        return urlConverterRequestService.convert(link)
    }

    @PostMapping("/deeplink-to-link")
    fun toLink(@RequestBody deepLink: TyDeepLink): Any {
        return urlConverterRequestService.convert(deepLink)
    }
}
