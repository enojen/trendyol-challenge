package com.trendyol.util

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import org.junit.Assert.assertEquals
import org.junit.Test

class LinkBuilderTest {

    @Test
    fun `build link with path`(){
        val link = LinkBuilder()
                    .addPath("/path1")
                    .addPath("/path2")
                    .buildLink()

        val expected = TyLink("https://www.trendyol.com/path1/path2")
        assertEquals(expected, link)
    }

    @Test
    fun `build link with params`(){
        val link = LinkBuilder()
                .addParam("p1", "P1")
                .addParam("p2", "P2")
                .buildLink()

        val expected = TyLink("https://www.trendyol.com?p1=P1&p2=P2")

        assertEquals(expected, link)
    }

    @Test
    fun `build link with path and params`(){
        val link = LinkBuilder()
                .addPath("/path1")
                .addPath("/path2")
                .addParam("p1", "P1")
                .addParam("p2", "P2")
                .buildLink()

        val expected = TyLink("https://www.trendyol.com/path1/path2?p1=P1&p2=P2")
        assertEquals(expected, link)
    }

    @Test
    fun `build deep link with path`(){
        val link = LinkBuilder()
                .addPath("/path1")
                .addPath("/path2")
                .buildDeepLink()

        val expected = TyDeepLink("ty:///path1/path2")
        assertEquals(expected, link)
    }

    @Test
    fun `build deep link with params`(){
        val link = LinkBuilder()
                .addParam("p1", "P1")
                .addParam("p2", "P2")
                .buildDeepLink()

        val expected = TyDeepLink("ty://?p1=P1&p2=P2")

        assertEquals(expected, link)
    }

    @Test
    fun `build deep link with path and params`(){
        val link = LinkBuilder()
                .addPath("/path1")
                .addPath("/path2")
                .addParam("p1", "P1")
                .addParam("p2", "P2")
                .buildDeepLink()

        val expected = TyDeepLink("ty:///path1/path2?p1=P1&p2=P2")
        assertEquals(expected, link)
    }

}