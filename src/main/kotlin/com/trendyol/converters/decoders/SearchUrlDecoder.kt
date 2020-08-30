package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.util.LinkBuilder


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