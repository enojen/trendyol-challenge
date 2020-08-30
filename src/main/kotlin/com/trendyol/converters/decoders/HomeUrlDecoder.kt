package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import com.trendyol.services.SectionService
import com.trendyol.util.LinkBuilder


data class HomeUrlDecoder(val sectionService: SectionService) : Decoder {
    override val predicate = { lnk: DeepLink ->
        lnk.params.contains(Pair("Page", "Home"))
    }

    override val decode = { lnk: DeepLink ->
        val lb = LinkBuilder("https://www.trendyol.com")
        val section: Pair<String, String>? = lnk.params.find { it.first == "SectionId" }

        section?.let {
            val sectionName = sectionService.getSectionName(it.second.toInt())
            lb.addPath("/butik/liste/$sectionName")
        }

        WebUrl(lb.build())
    }
}