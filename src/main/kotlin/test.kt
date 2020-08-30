import com.trendyol.model.TyDeepLink
import org.springframework.web.util.UriComponentsBuilder


class WebUrl

fun String.asDeepLink(): TyDeepLink {

    val a = TyDeepLink(this)

    return a
}

fun main(){

    val a= "localhost/%s".format("123")

    println(a)
}