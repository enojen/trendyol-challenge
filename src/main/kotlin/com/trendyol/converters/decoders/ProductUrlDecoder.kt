package com.trendyol.converters.decoders

import com.trendyol.converters.*
import com.trendyol.model.TyDeepLink
import com.trendyol.util.LinkBuilder


object ProductUrlDecoder : Decoder {
    override val predicate = { deepLink: TyDeepLink ->
        deepLink.params.contains(Pair(PAGE_PARAM, PRODUCT_PARAM))
    }

    override val decode = { deepLink: TyDeepLink ->
        val linkBuilder = LinkBuilder()

        deepLink.params.forEach {
            if (it.first == CONTENT_ID_PARAM)
                linkBuilder.addPath("/brand").addPath("/name-p-${it.second}")

            if (it.first == CAMPAIGN_ID)
                linkBuilder.addParam(BOUTIQUE_ID, it.second)

            if (it.first == MERCHANT_ID_PARAM)
                linkBuilder.addParam(MERCHANT_ID, it.second)
        }

        linkBuilder.buildLink()
    }
}