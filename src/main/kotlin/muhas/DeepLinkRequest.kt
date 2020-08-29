package muhas

data class DeepLinkRequest(val url: String)

data class DeepLinkResponse(val deepLink: DeepLink)