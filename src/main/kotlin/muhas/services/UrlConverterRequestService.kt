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
import muhas.repository.mysql.UrlRequest
import muhas.repository.mysql.UrlRequestRepo
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
        val urlRequest = UrlRequest(0, webUrl = webUrl.url, deepLink = deepLink.url)
        urlRequestRepo.save(urlRequest)
    }

}
