package com.trendyol.services

import com.trendyol.converters.decoders.DefaultUrlDecoder
import com.trendyol.converters.decoders.HomeUrlDecoder
import com.trendyol.converters.decoders.ProductUrlDecoder
import com.trendyol.converters.decoders.SearchUrlDecoder
import com.trendyol.converters.encoders.DefaultUrlEncoder
import com.trendyol.converters.encoders.HomeUrlEncoder
import com.trendyol.converters.encoders.ProductUrlEncoder
import com.trendyol.converters.encoders.SearchUrlEncoder
import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import org.springframework.stereotype.Service

@Service
class UrlConverterService(sectionService: SectionService) {

    val urlEncoders = listOf(
            HomeUrlEncoder(sectionService),
            ProductUrlEncoder,
            SearchUrlEncoder,
            DefaultUrlEncoder
    )

    val urlDecoders = listOf(
            HomeUrlDecoder(sectionService),
            ProductUrlDecoder,
            SearchUrlDecoder,
            DefaultUrlDecoder
    )

    fun toDeepLink(webUrl: WebUrl): DeepLink {
        return urlEncoders
                .first { it.predicate(webUrl) }
                .encode(webUrl)
    }

    fun toWebUrl(deepLink: DeepLink): WebUrl {
        return urlDecoders
                .first { it.predicate(deepLink) }
                .decode(deepLink)
    }

}
