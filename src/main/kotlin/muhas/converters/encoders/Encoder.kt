package muhas.converters.encoders

import muhas.model.DeepLink
import muhas.model.WebUrl

interface Encoder {
    val predicate: (WebUrl) -> Boolean
    val encode: (WebUrl) -> DeepLink
}