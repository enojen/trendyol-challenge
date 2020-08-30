package com.trendyol

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content


object TestHelper {

    val jsonResult = content().contentType(MediaType.APPLICATION_JSON);
}