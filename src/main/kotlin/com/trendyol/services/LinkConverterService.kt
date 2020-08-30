package com.trendyol.services

import com.trendyol.converters.decoders.DefaultUrlDecoder
import com.trendyol.converters.decoders.HomeUrlDecoder
import com.trendyol.converters.decoders.ProductUrlDecoder
import com.trendyol.converters.decoders.SearchUrlDecoder
import com.trendyol.converters.encoders.DefaultUrlEncoder
import com.trendyol.converters.encoders.HomeUrlEncoder
import com.trendyol.converters.encoders.ProductUrlEncoder
import com.trendyol.converters.encoders.SearchUrlEncoder
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.model.Uri
import org.springframework.stereotype.Service

@Service
class LinkConverterService(sectionService: SectionService) {

    val linkEncoders = listOf(
            HomeUrlEncoder(sectionService),
            ProductUrlEncoder,
            SearchUrlEncoder,
            DefaultUrlEncoder
    )

    val linkDecoders = listOf(
            HomeUrlDecoder(sectionService),
            ProductUrlDecoder,
            SearchUrlDecoder,
            DefaultUrlDecoder
    )

    fun toDeepLink(link: TyLink): TyDeepLink {
        return linkEncoders
                .first { it.predicate(link) }
                .encode(link)
    }

    fun toLink(deepLink: TyDeepLink): TyLink {
        return linkDecoders
                .first { it.predicate(deepLink) }
                .decode(deepLink)
    }
}
