package muhas.services

import muhas.DeepLink
import muhas.DeepLinkRequest
import muhas.DeepLinkResponse
import muhas.WebUrl
import muhas.converters.decoders.DefaultUrlDecoder
import muhas.converters.decoders.HomeUrlDecoder
import muhas.converters.decoders.ProductUrlDecoder
import muhas.converters.decoders.SearchUrlDecoder
import muhas.converters.encoders.DefaultUrlEncoder
import muhas.converters.encoders.HomeUrlEncoder
import muhas.converters.encoders.ProductUrlEncoder
import muhas.converters.encoders.SearchUrlEncoder
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

    fun toDeepLink(request: DeepLinkRequest): DeepLinkResponse {
        val webUrl = WebUrl(request.url)
        return urlEncoders
                .first { it.predicate(webUrl) }
                .encode(webUrl)
                .let { DeepLinkResponse(it) }
    }

    fun toUrl(request: DeepLinkRequest): WebUrl {
        val deepLink = DeepLink(request.url)
        return urlDecoders
                .first { it.predicate(deepLink) }
                .decode(deepLink)

    }

}
