package muhas

import org.springframework.data.annotation.Id
import java.io.Serializable
import java.net.URL



data class WebUrl(val url: String) {
    private val parsedUrl = URL(url)

    val path = parsedUrl.path

    // TODO parametresiz patlıyor
    val params = parsedUrl.query?.split("&")?.map {
        Pair(it.split("=")[0], it.split("=")[1])
    }.orEmpty()
}


data class DeepLink(val url: String) {
    val params = url.replace("ty://?", "").split("&").map {
        Pair(it.split("=")[0], it.split("=")[1])
    }
}


data class ShortLink(
        @Id
        val shortLink: String,
        val deepLink: String,
        val webUrl: String
) : Serializable


