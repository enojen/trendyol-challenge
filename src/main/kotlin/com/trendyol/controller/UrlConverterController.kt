package com.trendyol.controller

import com.trendyol.DeepLinkRequest
import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.services.UrlConverterRequestService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UrlConverterController(val urlConverterRequestService: UrlConverterRequestService) {

    @PostMapping("/deeplink")
    fun toDeeplink(@RequestBody a: DeepLinkRequest): Any {
        return urlConverterRequestService.convert(WebUrl(a.url))
    }

    @PostMapping("/url")
    fun toUrl(@RequestBody a: DeepLinkRequest): Any {
        return urlConverterRequestService.convert(DeepLink(a.url))
    }
}
