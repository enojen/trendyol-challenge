package muhas.converters.decoders

import muhas.DeepLink
import muhas.WebUrl
import muhas.services.SectionService
import muhas.util.LinkBuilder


object ProductUrlDecoder : Decoder {
    override val predicate = { lnk: DeepLink ->
        lnk.params.contains(Pair("Page", "Product"))
    }

    override val decode = { lnk: DeepLink ->
        val lb = LinkBuilder("https://www.trendyol.com")

        lnk.params.forEach {
            if (it.first == "ContentId")
                lb.addPath("/brand/name-p-${it.second}")

            if (it.first == "CampaignId")
                lb.addParam("boutiqueId" to it.second)

            if (it.first == "MerchantId")
                lb.addParam("merchantId" to it.second)
        }

        WebUrl(lb.build())
    }
}