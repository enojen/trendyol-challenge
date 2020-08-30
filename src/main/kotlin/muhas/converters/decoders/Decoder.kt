package muhas.converters.decoders

import muhas.model.DeepLink
import muhas.model.WebUrl

interface Decoder {
    val predicate: (DeepLink) -> Boolean
    val decode: (DeepLink) -> WebUrl
}