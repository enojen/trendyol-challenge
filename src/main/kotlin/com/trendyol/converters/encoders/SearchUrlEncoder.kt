package com.trendyol.converters.encoders

import com.trendyol.converters.*
import com.trendyol.model.TyLink
import com.trendyol.util.LinkBuilder


object SearchUrlEncoder : Encoder {

    override val predicate = { link: TyLink ->
        link.path == ALL_PRODUCTS && link.hasParam(Q_PARAM)
    }

    override val encode = { link: TyLink ->
        val linkBuilder = LinkBuilder().addParam(PAGE_PARAM, SEARCH_PARAM)

        link.params.forEach {
            if (it.first == Q_PARAM)
                linkBuilder.addParam(QUERY_PARAM, it.second)
        }

        linkBuilder.buildDeepLink()
    }
}