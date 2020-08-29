package muhas.converters.encoders

import muhas.DeepLink
import muhas.WebUrl

interface Encoder {
    val predicate: (WebUrl) -> Boolean
    val encode: (WebUrl) -> DeepLink
}