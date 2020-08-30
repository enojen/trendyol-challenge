package com.trendyol.converters.decoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ProductUrlDecoderTest {

    @Test
    fun `decode to product page`() {
        val deeplink = DeepLink("ty://?Page=Product&ContentId=123")

        val condition = ProductUrlDecoder.predicate(deeplink)
        val result = ProductUrlDecoder.decode(deeplink)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(WebUrl("https://www.trendyol.com/brand/name-p-123"), result)
    }

    @Test
    fun `decode to product page with boutiqueId`() {
        val deeplink = DeepLink("ty://?Page=Product&ContentId=11&CampaignId=22")

        val condition = ProductUrlDecoder.predicate(deeplink)
        val result = ProductUrlDecoder.decode(deeplink)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(WebUrl("https://www.trendyol.com/brand/name-p-11?boutiqueId=22"), result)
    }

    @Test
    fun `decode to product page with merchantId`() {
        val deeplink = DeepLink("ty://?Page=Product&ContentId=11&MerchantId=22")

        val condition = ProductUrlDecoder.predicate(deeplink)
        val result = ProductUrlDecoder.decode(deeplink)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(WebUrl("https://www.trendyol.com/brand/name-p-11?merchantId=22"), result)
    }

    @Test
    fun `decode to product page with merchantId and boutiqueId`() {
        val deeplink = DeepLink("ty://?Page=Product&ContentId=11&MerchantId=22&CampaignId=33")

        val condition = ProductUrlDecoder.predicate(deeplink)
        val result = ProductUrlDecoder.decode(deeplink)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(WebUrl("https://www.trendyol.com/brand/name-p-11?merchantId=22&boutiqueId=33"), result)
    }



}