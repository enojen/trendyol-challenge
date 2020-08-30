package com.trendyol.converters.encoders

import com.trendyol.converters.*
import com.trendyol.model.TyLink
import com.trendyol.util.LinkBuilder

object ProductUrlEncoder : Encoder {
    private val regex = Regex("(.*)-p-(\\d+)")

    override val predicate = { link: TyLink ->
        link.pathSegments.size == 2 && regex.matches(link.pathSegments[1])
    }

    override val encode = { link: TyLink ->
        val linkBuilder = LinkBuilder()
        val contentId = getContentId(link)

        linkBuilder.addParam(PAGE_PARAM, PRODUCT_PARAM)
                   .addParam(CONTENT_ID_PARAM, contentId)

        link.params.forEach {
            if (it.first == BOUTIQUE_ID_PARAM)
                linkBuilder.addParam(CAMPAIGN_ID_PARAM, it.second)
            if (it.first == MERCHANT_ID_PARAM_LOWER)
                linkBuilder.addParam(MERCHANT_ID_PARAM_UPPER, it.second)
        }

        linkBuilder.buildDeepLink()
    }

    private fun getContentId(link: TyLink): String {
        val productSegment = link.pathSegments[1]
        val (_, contentId) = regex.find(productSegment)!!.destructured
        return contentId
    }
}