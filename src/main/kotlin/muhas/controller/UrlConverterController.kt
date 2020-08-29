package muhas.controller

import muhas.DeepLinkRequest
import muhas.services.UrlConverterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UrlConverterController(val urlConverterService: UrlConverterService) {

    @PostMapping("/deeplink")
    fun toDeeplink(@RequestBody a: DeepLinkRequest): Any {

        val result = urlConverterService.toDeepLink(a)

        println(result)
        return result
    }

    @PostMapping("/url")
    fun toUrl(@RequestBody a: DeepLinkRequest): Any {

        val result = urlConverterService.toUrl(a)

        println(result)
        return result
    }
}
