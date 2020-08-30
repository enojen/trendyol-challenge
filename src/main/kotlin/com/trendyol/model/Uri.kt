package com.trendyol.model

import org.springframework.web.util.UriComponentsBuilder

abstract class Uri(uri: String) {

    private val uriParts = UriComponentsBuilder.fromUriString(uri).build()

    val params = uriParts.queryParams.map {
        Pair(it.key, it.value[0])
    }

    val path = uriParts.path!!
    val schema = uriParts.scheme!!
    val host = uriParts.host!!
    val pathSegments: List<String> = uriParts.pathSegments

    fun hasParam(paramName: String): Boolean {
        val pair = params.firstOrNull { it.first == paramName }
        return pair != null
    }

    fun getParam(paramName: String): Pair<String, String>? {
        return params.firstOrNull { it.first == paramName }
    }
}