package com.trendyol.model

data class Link(val url: String) : Uri(url)


data class TyLink(val url: String) : Uri(url) {
    init {
        require(schema in arrayOf("http", "https")) { "Invalid schema in $url -> $schema" }
        require(host in arrayOf("trendyol.com", "www.trendyol.com")) { "Invalid host in $url -> $host" }
    }
}


data class TyDeepLink(val url: String) : Uri(url) {
    init {
        require(schema == "ty") { "Invalid schema -> $schema" }
    }
}

