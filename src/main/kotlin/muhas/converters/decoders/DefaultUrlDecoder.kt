package muhas.converters.decoders

import muhas.DeepLink
import muhas.WebUrl


object DefaultUrlDecoder : Decoder {
    override val predicate = { lnk: DeepLink -> true }

    override val decode = { lnk: DeepLink ->
        WebUrl("https://www.trendyol.com")
    }
}