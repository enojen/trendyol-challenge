package muhas

import java.io.Serializable
import java.net.URL
import javax.persistence.Entity
import javax.persistence.Table


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

@Entity
@Table
class ShortLink(
        @javax.persistence.Id
        var shortLink: String,
        var deepLink: String,
        var webUrl: String
) : Serializable


