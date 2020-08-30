package com.trendyol.converters.encoders

import com.trendyol.converters.HOME_PARAM
import com.trendyol.converters.PAGE_PARAM
import com.trendyol.converters.SECTION_ID_PARAM
import com.trendyol.model.TyLink
import com.trendyol.services.SectionService
import com.trendyol.util.LinkBuilder

data class HomeUrlEncoder(val sectionService: SectionService) : Encoder {

    override val predicate = { link: TyLink ->
        link.path.startsWith("/butik/liste/")
                || (link.path == "/")
                || (link.path == "")
                || (link.path == "/butik/liste")
    }

    override val encode = { link: TyLink ->
        val linkBuilder = LinkBuilder().addParam(PAGE_PARAM, HOME_PARAM)

        if (link.pathSegments.size == 3) {
            val sectionName = link.pathSegments[2]
            val sectionId = sectionService.getSectionId(sectionName)
            linkBuilder.addParam(SECTION_ID_PARAM, sectionId.toString())
        }

        linkBuilder.buildDeepLink()
    }
}