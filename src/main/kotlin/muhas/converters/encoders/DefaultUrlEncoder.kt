package muhas.converters.encoders

import muhas.model.DeepLink
import muhas.model.WebUrl


object DefaultUrlEncoder : Encoder {
    override val predicate = { url: WebUrl -> true }

    override val encode = { p: WebUrl ->
        DeepLink("ty://?Page=Home")
    }
}