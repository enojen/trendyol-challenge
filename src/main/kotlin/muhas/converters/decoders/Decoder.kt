package muhas.converters.decoders

import muhas.DeepLink
import muhas.WebUrl

interface Decoder {
    val predicate: (DeepLink) -> Boolean
    val decode: (DeepLink) -> WebUrl
}