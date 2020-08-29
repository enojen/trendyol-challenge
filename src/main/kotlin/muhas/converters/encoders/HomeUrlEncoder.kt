package muhas.converters.encoders

import muhas.*
import muhas.services.SectionService
import muhas.util.LinkBuilder

data class HomeUrlEncoder(val sectionService: SectionService) : Encoder {

    override val predicate = { url: WebUrl ->
        url.path.startsWith("/butik/liste/")
                || (url.path == "/")
                || (url.path == "")
                || (url.path == "/butik/liste")
    }

    override val encode = { url: WebUrl ->
        val linkBuilder = LinkBuilder("ty://").addParam(Pair("Page", "Home"))

        val paths = url.path.split("/")

        if (paths.size == 3) {
            val sectionName = paths[2]
            val sectionId = sectionService.getSectionId(sectionName)
            linkBuilder.addParam(Pair("SectionId", sectionId.toString()))
        }

        DeepLink(linkBuilder.build())
    }
}