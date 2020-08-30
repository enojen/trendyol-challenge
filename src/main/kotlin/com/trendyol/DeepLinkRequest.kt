package com.trendyol

import com.trendyol.model.DeepLink

data class DeepLinkRequest(val url: String)

data class DeepLinkResponse(val deepLink: DeepLink)