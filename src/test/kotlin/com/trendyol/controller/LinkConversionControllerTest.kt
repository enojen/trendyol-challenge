package com.trendyol.controller


import com.ninjasquad.springmockk.MockkBean
import com.trendyol.TestHelper.jsonResult
import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink
import com.trendyol.services.LinkConversionService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(controllers = [LinkConversionController::class])
class LinkConversionControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var conversionService: LinkConversionService

    @Test
    fun `convert link to deep link`() {
        val link = TyLink("https://trendyol.com/butik/liste/kadin")
        val deepLink = TyDeepLink("ty://?Page=Home&SectionId=1")

        val request = """{ "url" : "${link.url}" } """.trimIndent()

        every { conversionService.toDeepLink(link) } returns deepLink

        mockMvc.perform(post("/api/convert/link-to-deeplink")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonResult)
                .andExpect(jsonPath("$.deepLink").value(deepLink.url))
    }

    @Test
    fun `convert deep link to link`() {
        val link = TyLink("https://trendyol.com/butik/liste/kadin")
        val deepLink = TyDeepLink("ty://?Page=Home&SectionId=1")

        val request = """{ "url" : "${deepLink.url}" } """.trimIndent()

        every { conversionService.toLink(deepLink) } returns link

        mockMvc.perform(post("/api/convert/deeplink-to-link")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonResult)
                .andExpect(jsonPath("$.link").value(link.url))
    }

    @Test
    fun `handle invalid link`() {
        val request = """{ "url" : "http://www.google.com" } """.trimIndent()

        mockMvc.perform(post("/api/convert/link-to-deeplink")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(jsonResult)
                .andExpect(jsonPath("$.message").value("Invalid input"))
    }

}