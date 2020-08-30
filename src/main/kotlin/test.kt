import com.trendyol.model.TyDeepLink
import org.springframework.web.util.UriComponentsBuilder


class WebUrl

fun String.asDeepLink(): TyDeepLink {

    val a = TyDeepLink(this)

    return a
}

fun main(){

    val v = "ty://?as=as".asDeepLink()

    val d = TyDeepLink("ty://?as=asd")

    val a = UriComponentsBuilder.newInstance()
            .scheme("ty")
            .queryParam("12", "123")
            .path("path1")
            .path("/path2")
            .build()
    println(a)

    val b = UriComponentsBuilder.fromHttpUrl("http://localhost/f?asD=as&asD=23").build()
    val c = UriComponentsBuilder.fromUriString("ty://?Page=Home").build()

    println(b.queryParams)
    println(c.queryParams)
    println(c.pathSegments)
}