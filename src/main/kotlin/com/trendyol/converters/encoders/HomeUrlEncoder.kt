package com.trendyol.converters.encoders

import com.trendyol.converters.*
import com.trendyol.model.TyLink
import com.trendyol.services.SectionService
import com.trendyol.util.LinkBuilder

data class HomeUrlEncoder(val sectionService: SectionService) : Encoder {

    override val predicate = { link: TyLink ->
        link.path.startsWith(HOME_PATH) or (link.path == SLASH) or (link.path.isBlank())
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