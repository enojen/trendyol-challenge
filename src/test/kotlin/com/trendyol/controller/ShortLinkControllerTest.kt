package com.trendyol.controller


import com.ninjasquad.springmockk.MockkBean
import com.trendyol.ShortLinkNotFoundException
import com.trendyol.TestHelper.jsonResult
import com.trendyol.model.Link
import com.trendyol.model.ShortLink
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.services.ShortLinkService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(controllers = [ShortLinkController::class])
class ShortLinkControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var shortLinkService: ShortLinkService

    @Test
    fun `create short link from link`() {
        val link = TyLink("https://trendyol.com")
        val shortLink = ShortLink(shortLink = "ab", deepLink = "deep-link", link = "link")

        val request = """{ "url" : "${link.url}" } """.trimIndent()

        every { shortLinkService.createFromLink(link) } returns shortLink

        mockMvc.perform(post("/api/shortlink/from-link")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonResult)
                .andExpect(jsonPath("$.shortLink").value("http://localhost:8080/ab"))
    }

    @Test
    fun `create short link from deep link`() {
        val deepLink = TyDeepLink("ty://?Page=Home")
        val shortLink = ShortLink(shortLink = "ab", deepLink = "deep-link", link = "link")

        val request = """{ "url" : "${deepLink.url}" } """.trimIndent()

        every { shortLinkService.createFromDeepLink(deepLink) } returns shortLink

        mockMvc.perform(post("/api/shortlink/from-deeplink")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonResult)
                .andExpect(jsonPath("$.shortLink").value("http://localhost:8080/ab"))
    }

    @Test
    fun `get short link details`() {
        val link = Link("http://localhost/ab")
        val shortLink = ShortLink(shortLink = "ab", deepLink = "deep-link", link = "link")

        val request = """{ "url" : "${link.url}" } """.trimIndent()

        every { shortLinkService.findShortLink(link) } returns shortLink

        mockMvc.perform(get("/api/shortlink")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonResult)
                .andExpect(jsonPath("$.shortLink").value("http://localhost:8080/ab"))
                .andExpect(jsonPath("$.deepLink").value("deep-link"))
                .andExpect(jsonPath("$.link").value("link"))
    }

    @Test
    fun `handle not found short link`() {
        val request = """{ "url" : "http://some-url" } """.trimIndent()

        every { shortLinkService.findShortLink(any()) } throws ShortLinkNotFoundException()

        mockMvc.perform(get("/api/shortlink")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound)
                .andExpect(jsonResult)
                .andExpect(jsonPath("$.message").value("Short link not found"))
    }
}