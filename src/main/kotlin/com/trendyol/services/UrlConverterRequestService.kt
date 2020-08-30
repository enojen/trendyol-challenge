package com.trendyol.services

import com.trendyol.model.DeepLink
import com.trendyol.model.UrlRequest
import com.trendyol.model.WebUrl
import com.trendyol.repository.mysql.UrlRequestRepo
import org.springframework.stereotype.Service

@Service
class UrlConverterRequestService(val urlRequestRepo: UrlRequestRepo, val urlConverterService: UrlConverterService) {

    fun convert(webUrl: WebUrl): DeepLink {
        val deepLink = urlConverterService.toDeepLink(webUrl)
        saveRequest(webUrl, deepLink)
        return deepLink
    }

    fun convert(deepLink: DeepLink): WebUrl {
        val webUrl = urlConverterService.toWebUrl(deepLink)
        saveRequest(webUrl, deepLink)
        return webUrl
    }

    fun saveRequest(webUrl: WebUrl, deepLink: DeepLink) {
        val urlRequest = UrlRequest(id = 0, webUrl = webUrl.url, deepLink = deepLink.url)
        urlRequestRepo.save(urlRequest)
    }

}
