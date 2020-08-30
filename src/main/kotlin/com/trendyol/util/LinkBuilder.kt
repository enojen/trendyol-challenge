package com.trendyol.util

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.springframework.web.util.UriComponentsBuilder

class LinkBuilder {

    private val builder = UriComponentsBuilder.newInstance()

    fun addPath(path: String): LinkBuilder {
        builder.path(path)
        return this
    }

    fun addParam(name: String, value: String): LinkBuilder {
        builder.queryParam(name, value)
        return this
    }

    fun buildDeepLink(): TyDeepLink {
        val uri = this.buildUri("ty", "")
        return TyDeepLink(uri)
    }

    fun buildLink(): TyLink {
        val uri = this.buildUri("https", "www.trendyol.com")
        return TyLink(uri)
    }

    fun buildUri(schema: String, host: String): String {
        return builder
                .scheme(schema)
                .host(host)
                .build()
                .toUriString()
    }

    fun buildLink(schema: String = "http", host: String): String {
        return builder
                .scheme(schema)
                .host(host)
                .build()
                .toUriString()
    }
}