package com.trendyol.converters.decoders

import com.trendyol.converters.*
import com.trendyol.model.TyDeepLink
import com.trendyol.util.LinkBuilder


object SearchUrlDecoder : Decoder {
    override val predicate = { deepLink: TyDeepLink ->
        deepLink.params.contains(Pair(PAGE_PARAM, SEARCH_PARAM)) and deepLink.hasParam(QUERY_PARAM)
    }

    override val decode = { deepLink: TyDeepLink ->
        val queryParam = deepLink.getParam(QUERY_PARAM)!!

        LinkBuilder()
                .addPath(ALL_PRODUCTS)
                .addParam(Q_PARAM, queryParam.second)
                .buildLink()

    }
}