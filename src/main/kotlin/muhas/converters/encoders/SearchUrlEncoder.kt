package muhas.converters.encoders

import muhas.model.DeepLink
import muhas.model.WebUrl
import muhas.util.LinkBuilder

object SearchUrlEncoder : Encoder {
    override val predicate = { p: WebUrl -> p.path == "/tum--urunler" }

    override val encode = { url: WebUrl ->
        val linkBuilder = LinkBuilder("ty://").addParam(Pair("Page", "Search"))

        url.params.forEach {
            if (it.first == "q")
                linkBuilder.addParam("Query" to it.second)
        }

        DeepLink(linkBuilder.build())
    }
}