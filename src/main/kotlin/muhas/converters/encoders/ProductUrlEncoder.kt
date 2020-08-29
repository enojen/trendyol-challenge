package muhas.converters.encoders

import muhas.DeepLink
import muhas.util.LinkBuilder
import muhas.WebUrl

object ProductUrlEncoder : Encoder {
    private val regex = Regex("(.*)-p-(\\d+)")

    override val predicate = { url: WebUrl ->
        val paths = url.path.split("/")
        paths.size == 3 && regex.matches(paths[2])
    }

    override val encode = { url: WebUrl ->
        val linkBuilder = LinkBuilder("ty://")
        val contentId = getContentId(url)

        linkBuilder.addParam(Pair("Page", "Product"))
                   .addParam(Pair("ContentId", contentId))

        url.params.forEach {
            if (it.first == "boutiqueId")
                linkBuilder.addParam(Pair("CampaignId", it.second))
            if (it.first == "merchantId")
                linkBuilder.addParam(Pair("MerchantId", it.second))
        }

        DeepLink(linkBuilder.build())
    }

    private fun getContentId(url: WebUrl): String {
        val productNamePath = url.path.split("/")[2]
        val (_, contentId) = regex.find(productNamePath)!!.destructured
        return contentId
    }
}