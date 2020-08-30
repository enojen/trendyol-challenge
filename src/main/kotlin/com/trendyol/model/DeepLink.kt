package com.trendyol.model

data class DeepLink(val url: String) {
    private val PROTOCOL = "ty://?"

    val params = url.replace(PROTOCOL, "").split("&").map {
        Pair(it.split("=")[0], it.split("=")[1])
    }
}