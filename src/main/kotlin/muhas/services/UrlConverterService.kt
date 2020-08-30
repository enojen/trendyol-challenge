package muhas.services

import muhas.converters.decoders.DefaultUrlDecoder
import muhas.converters.decoders.HomeUrlDecoder
import muhas.converters.decoders.ProductUrlDecoder
import muhas.converters.decoders.SearchUrlDecoder
import muhas.converters.encoders.DefaultUrlEncoder
import muhas.converters.encoders.HomeUrlEncoder
import muhas.converters.encoders.ProductUrlEncoder
import muhas.converters.encoders.SearchUrlEncoder
import muhas.model.DeepLink
import muhas.model.WebUrl
import org.springframework.stereotype.Service

@Service
class UrlConverterService(sectionMappings: SectionService) {

    val urlEncoders = listOf(
            HomeUrlEncoder(sectionMappings),
            ProductUrlEncoder,
            SearchUrlEncoder,
            DefaultUrlEncoder
    )

    val urlDecoders = listOf(
            HomeUrlDecoder(sectionMappings),
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
