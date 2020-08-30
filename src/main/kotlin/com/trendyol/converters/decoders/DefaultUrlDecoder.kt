package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl


object DefaultUrlDecoder : Decoder {
    override val predicate = { lnk: DeepLink -> true }

    override val decode = { lnk: DeepLink ->
        WebUrl("https://www.trendyol.com")
    }
}