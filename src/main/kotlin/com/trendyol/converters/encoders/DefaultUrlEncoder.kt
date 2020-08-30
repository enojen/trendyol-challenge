package com.trendyol.converters.encoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl


object DefaultUrlEncoder : Encoder {
    override val predicate = { url: WebUrl -> true }

    override val encode = { p: WebUrl ->
        DeepLink("ty://?Page=Home")
    }
}