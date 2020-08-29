package muhas.converters.encoders

import muhas.DeepLink
import muhas.util.LinkBuilder
import muhas.WebUrl

object SearchUrlEncoder : Encoder {
    override val predicate = { p: WebUrl -> p.path == "/tum--urunler" }

    override val encode = { url: WebUrl ->
        val linkBuilder = LinkBuilder("ty://").addParam(Pair("Page", "Search"))

        url.params.forEach { linkBuilder.addParam(it) }

        DeepLink(linkBuilder.build())
    }
}