package com.trendyol.services

import com.trendyol.model.TyDeepLink
import com.trendyol.model.UrlRequest
import com.trendyol.model.TyLink
import com.trendyol.repository.mysql.UrlRequestRepo
import org.springframework.stereotype.Service

@Service
class LinkConverterRequestService(val urlRequestRepo: UrlRequestRepo, val linkConverterService: LinkConverterService) {

    fun convert(link: TyLink): TyDeepLink {
        val deepLink = linkConverterService.toDeepLink(link)
        saveRequest(link, deepLink)
        return deepLink
    }

    fun convert(deepLink: TyDeepLink): TyLink {
        val link = linkConverterService.toLink(deepLink)
        saveRequest(link, deepLink)
        return link
    }

    private fun saveRequest(link: TyLink, deepLink: TyDeepLink) {
        val urlRequest = UrlRequest(id = 0, link = link.url, deepLink = deepLink.url)
        urlRequestRepo.save(urlRequest)
    }

}
