package com.trendyol.converters.decoders

import com.trendyol.converters.HOME_PARAM
import com.trendyol.converters.PAGE_PARAM
import com.trendyol.converters.SECTION_ID
import com.trendyol.model.TyDeepLink
import com.trendyol.services.SectionService
import com.trendyol.util.LinkBuilder


data class HomeUrlDecoder(val sectionService: SectionService) : Decoder {
    override val predicate = { deepLink: TyDeepLink ->
        deepLink.params.contains(Pair(PAGE_PARAM, HOME_PARAM))
    }

    override val decode = { deepLink: TyDeepLink ->
        val linkBuilder = LinkBuilder()
        val sectionIdParam = deepLink.getParam(SECTION_ID)

        sectionIdParam?.let {
            val sectionName = sectionService.getSectionName(it.second.toInt())
            linkBuilder.addPath("/butik/liste/$sectionName")
        }

        linkBuilder.buildLink()
    }
}