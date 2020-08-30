package com.trendyol.services

import com.trendyol.model.LinkConversion
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.model.Uri
import com.trendyol.repository.mysql.LinkConversionRepo
import org.springframework.stereotype.Service

@Service
class LinkConversionService(val linkConversionRepo: LinkConversionRepo,
                            val linkConverter: LinkConverter) {

    fun toDeepLink(link: TyLink): TyDeepLink {
        val deepLink = linkConverter.toDeepLink(link)
        saveConversion(source = link, target = deepLink)
        return deepLink
    }

    fun toLink(deepLink: TyDeepLink): TyLink {
        val link = linkConverter.toLink(deepLink)
        saveConversion(source = deepLink, target = link)
        return link
    }

    private fun saveConversion(source: Uri, target: Uri) {
        val linkConversion = LinkConversion(id = 0, source = source.uri, target = target.uri)
        linkConversionRepo.save(linkConversion)
    }

}
