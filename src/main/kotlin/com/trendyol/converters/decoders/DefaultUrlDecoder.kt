package com.trendyol.converters.decoders

import com.trendyol.converters.TRENDYOL_HOMEPAGE_LINK
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink


object DefaultUrlDecoder : Decoder {
    override val predicate = { deepLink: TyDeepLink -> true }

    override val decode = { deepLink: TyDeepLink ->
        TyLink(TRENDYOL_HOMEPAGE_LINK)
    }
}



