package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl

interface Decoder {
    val predicate: (DeepLink) -> Boolean
    val decode: (DeepLink) -> WebUrl
}