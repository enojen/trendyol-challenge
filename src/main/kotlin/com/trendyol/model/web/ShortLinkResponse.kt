package com.trendyol.model.web

data class ShortLinkResponse(val webUrl: String, val deepLink: String, val error: String? = null)