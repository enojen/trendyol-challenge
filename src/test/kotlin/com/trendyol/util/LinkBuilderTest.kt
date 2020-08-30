package com.trendyol.util

import org.junit.Assert.assertEquals
import org.junit.Test

class LinkBuilderTest {

    @Test
    fun `host only url`(){
        val url = LinkBuilder("www.some-host.com").build()
        assertEquals("www.some-host.com", url)
    }

    @Test
    fun `build url with path`(){
        val url = LinkBuilder("www.some-host.com")
                .addPath("path1")
                .addPath("path2")
                .build()

        assertEquals("www.some-host.com/path1/path2", url)
    }

    @Test
    fun `build url with params`(){
        val url = LinkBuilder("www.some-host.com")
                .addParam("p1" to "P1")
                .addParam("p2" to "P2")
                .build()

        assertEquals("www.some-host.com/?p1=P1&p2=P2", url)
    }

    @Test
    fun `build url with path and params`(){
        val url = LinkBuilder("www.some-host.com")
                .addPath("path1")
                .addPath("path2")
                .addParam("p1" to "P1")
                .addParam("p2" to "P2")
                .build()

        assertEquals("www.some-host.com/path1/path2/?p1=P1&p2=P2", url)
    }
}