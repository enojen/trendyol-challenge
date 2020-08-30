package com.trendyol.model.web

import org.apache.commons.validator.routines.UrlValidator
import org.springframework.web.util.UriComponentsBuilder


data class ShortLinkResponse(val webUrl: String, val deepLink: String, val error: String? = null)


class Url(val url: String) {
    init {

    }
}




