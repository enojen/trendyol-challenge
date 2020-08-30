package com.trendyol.converters.encoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ProductUrlEncoderTest {

    @Test
    fun `encode to product url page without params`() {
        val link = TyLink("http://trendyol.com/brand/model-p-1234")

        val condition = ProductUrlEncoder.predicate(link)
        val result = ProductUrlEncoder.encode(link)

        assertEquals(true, condition)
        assertEquals(TyDeepLink("ty://?Page=Product&ContentId=1234"), result)
    }

    @Test
    fun `encode to product url page with CampaingId`() {
        val link = TyLink("http://trendyol.com/brand/model-p-1234?boutiqueId=11")

        val condition = ProductUrlEncoder.predicate(link)
        val result = ProductUrlEncoder.encode(link)

        assertEquals(true, condition)
        assertEquals(TyDeepLink("ty://?Page=Product&ContentId=1234&CampaignId=11"), result)
    }

    @Test
    fun `encode to product url page with MerhchantId`() {
        val link = TyLink("http://trendyol.com/brand/model-p-1234?merchantId=11")

        val condition = ProductUrlEncoder.predicate(link)
        val result = ProductUrlEncoder.encode(link)

        assertEquals(true, condition)
        assertEquals(TyDeepLink("ty://?Page=Product&ContentId=1234&MerchantId=11"), result)
    }

    @Test
    fun `encode to product url page with CampaingId and MerchantId`() {
        val link = TyLink("http://trendyol.com/brand/model-p-1234?boutiqueId=11&merchantId=22")

        val condition = ProductUrlEncoder.predicate(link)
        val result = ProductUrlEncoder.encode(link)

        assertEquals(true, condition)
        assertEquals(TyDeepLink("ty://?Page=Product&ContentId=1234&CampaignId=11&MerchantId=22"), result)
    }

    @Test
    fun `ignore if ContentId is not number`() {
        val link = TyLink("http://trendyol.com/brand/model-p-XXXX")
        val condition = ProductUrlEncoder.predicate(link)

        assertEquals(false, condition)
    }

}