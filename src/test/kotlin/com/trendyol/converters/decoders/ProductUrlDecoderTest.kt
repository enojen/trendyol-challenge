package com.trendyol.converters.decoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class ProductUrlDecoderTest {

    @Test
    fun `decode to product page`() {
        val deepLink = TyDeepLink("ty://?Page=Product&ContentId=123")

        val condition = ProductUrlDecoder.predicate(deepLink)
        val result = ProductUrlDecoder.decode(deepLink)

        assertEquals(true, condition)
        assertEquals(TyLink("https://www.trendyol.com/brand/name-p-123"), result)
    }

    @Test
    fun `decode to product page with boutiqueId`() {
        val deepLink = TyDeepLink("ty://?Page=Product&ContentId=11&CampaignId=22")

        val condition = ProductUrlDecoder.predicate(deepLink)
        val result = ProductUrlDecoder.decode(deepLink)

        assertEquals(true, condition)
        assertEquals(TyLink("https://www.trendyol.com/brand/name-p-11?boutiqueId=22"), result)
    }

    @Test
    fun `decode to product page with merchantId`() {
        val deepLink = TyDeepLink("ty://?Page=Product&ContentId=11&MerchantId=22")

        val condition = ProductUrlDecoder.predicate(deepLink)
        val result = ProductUrlDecoder.decode(deepLink)

        assertEquals(true, condition)
        assertEquals(TyLink("https://www.trendyol.com/brand/name-p-11?merchantId=22"), result)
    }

    @Test
    fun `decode to product page with merchantId and boutiqueId`() {
        val deepLink = TyDeepLink("ty://?Page=Product&ContentId=11&MerchantId=22&CampaignId=33")

        val condition = ProductUrlDecoder.predicate(deepLink)
        val result = ProductUrlDecoder.decode(deepLink)

        assertEquals(true, condition)
        assertEquals(TyLink("https://www.trendyol.com/brand/name-p-11?merchantId=22&boutiqueId=33"), result)
    }



}