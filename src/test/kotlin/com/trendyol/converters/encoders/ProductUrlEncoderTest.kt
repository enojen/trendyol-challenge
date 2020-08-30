package com.trendyol.converters.encoders

import muhas.converters.encoders.ProductUrlEncoder
import muhas.model.DeepLink
import muhas.model.WebUrl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class ProductUrlEncoderTest {

    @Test
    fun `encode to product url page without params`() {
        val webUrl = WebUrl("http://trendyol.com/brand/model-p-1234")

        val condition = ProductUrlEncoder.predicate(webUrl)
        val result = ProductUrlEncoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Product&ContentId=1234"), result)
    }

    @Test
    fun `encode to product url page with CampaingId`() {
        val webUrl = WebUrl("http://trendyol.com/brand/model-p-1234?boutiqueId=11")

        val condition = ProductUrlEncoder.predicate(webUrl)
        val result = ProductUrlEncoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Product&ContentId=1234&CampaignId=11"), result)
    }

    @Test
    fun `encode to product url page with MerhchantId`() {
        val webUrl = WebUrl("http://trendyol.com/brand/model-p-1234?merchantId=11")

        val condition = ProductUrlEncoder.predicate(webUrl)
        val result = ProductUrlEncoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Product&ContentId=1234&MerchantId=11"), result)
    }

    @Test
    fun `encode to product url page with CampaingId and MerchantId`() {
        val webUrl = WebUrl("http://trendyol.com/brand/model-p-1234?boutiqueId=11&merchantId=22")

        val condition = ProductUrlEncoder.predicate(webUrl)
        val result = ProductUrlEncoder.encode(webUrl)

        Assertions.assertEquals(true, condition)
        Assertions.assertEquals(DeepLink("ty://?Page=Product&ContentId=1234&CampaignId=11&MerchantId=22"), result)
    }

    @Test
    fun `ignore if ContentId is not number`() {
        val webUrl = WebUrl("http://trendyol.com/brand/model-p-XXXX")
        val condition = ProductUrlEncoder.predicate(webUrl)

        Assertions.assertEquals(false, condition)
    }

}