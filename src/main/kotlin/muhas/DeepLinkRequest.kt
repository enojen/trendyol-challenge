package muhas

import muhas.model.DeepLink

data class DeepLinkRequest(val url: String)

data class DeepLinkResponse(val deepLink: DeepLink)