package com.trendyol.controller

import com.trendyol.DeepLinkRequest
import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.services.UrlConverterRequestService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/convert")
class UrlConverterController(val urlConverterRequestService: UrlConverterRequestService) {

    @PostMapping("/weburl-to-deeplink")
    fun toDeeplink(@RequestBody a: DeepLinkRequest): Any {
        return urlConverterRequestService.convert(WebUrl(a.url))
    }

    @PostMapping("/deeplink-to-weburl")
    fun toUrl(@RequestBody a: DeepLinkRequest): Any {
        return urlConverterRequestService.convert(DeepLink(a.url))
    }
}
