package muhas.controller

import muhas.DeepLinkRequest
import muhas.model.DeepLink
import muhas.model.WebUrl
import muhas.services.UrlConverterRequestService
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
