package com.trendyol.converters.decoders

import com.trendyol.converters.*
import com.trendyol.model.TyDeepLink
import com.trendyol.util.LinkBuilder


object SearchUrlDecoder : Decoder {
    override val predicate = { deepLink: TyDeepLink ->
        deepLink.params.contains(Pair(PAGE_PARAM, SEARCH_PARAM))
    }

    override val decode = { deepLink: TyDeepLink ->
        val linkBuilder = LinkBuilder().addPath(ALL_PRODUCTS)
        val queryParam = deepLink.getParam(QUERY_PARAM)

        queryParam?.let {
            linkBuilder.addParam(Q_PARAM, it.second)
        }

        linkBuilder.buildLink()
    }
}