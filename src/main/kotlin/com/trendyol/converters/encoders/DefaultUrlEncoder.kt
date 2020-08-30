package com.trendyol.converters.encoders

import com.trendyol.converters.TRENDYOL_HOMEPAGE_DEEPLINK
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink


object DefaultUrlEncoder : Encoder {
    override val predicate = { link: TyLink -> true }

    override val encode = { link: TyLink ->
        TyDeepLink(TRENDYOL_HOMEPAGE_DEEPLINK)
    }
}