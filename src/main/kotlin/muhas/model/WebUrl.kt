package muhas.model

import java.net.URL

data class WebUrl(val url: String) {
    private val parsedUrl = URL(url)

    val path = parsedUrl.path

    // TODO parametresiz patlıyor
    val params = parsedUrl.query?.split("&")?.map {
        Pair(it.split("=")[0], it.split("=")[1])
    }.orEmpty()
}