package com.trendyol.converters.encoders

import com.trendyol.converters.*
import com.trendyol.model.TyLink
import com.trendyol.util.LinkBuilder


object SearchUrlEncoder : Encoder {

    override val predicate = { link: TyLink ->
        (link.path == ALL_PRODUCTS) and (link.hasParam(Q_PARAM))
    }

    override val encode = { link: TyLink ->
        val queryParam = link.getParam(Q_PARAM)!!

        LinkBuilder()
                .addParam(PAGE_PARAM, SEARCH_PARAM)
                .addParam(QUERY_PARAM, queryParam.second)
                .buildDeepLink()
    }
}