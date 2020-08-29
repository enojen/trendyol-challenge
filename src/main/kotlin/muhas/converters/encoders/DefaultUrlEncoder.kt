package muhas.converters.encoders

import muhas.DeepLink
import muhas.WebUrl


object DefaultUrlEncoder : Encoder {
    override val predicate = { url: WebUrl -> true }

    override val encode = { p: WebUrl ->
        DeepLink("Page=Home")
    }
}