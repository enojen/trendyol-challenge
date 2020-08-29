package muhas.converters.decoders

import muhas.DeepLink
import muhas.WebUrl
import muhas.services.SectionService
import muhas.util.LinkBuilder


object SearchUrlDecoder : Decoder {
    override val predicate = { lnk: DeepLink ->
        lnk.params.contains(Pair("Page", "Search"))
    }

    override val decode = { lnk: DeepLink ->
        val lb = LinkBuilder("https://www.trendyol.com")

        lb.addPath("/tum--urunler")

        lnk.params.forEach {
            if (it.first == "Query")
                lb.addParam("q" to it.second)
        }

        WebUrl(lb.build())
    }
}