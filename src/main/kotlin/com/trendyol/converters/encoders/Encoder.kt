package com.trendyol.converters.encoders

import com.trendyol.model.DeepLink
import com.trendyol.model.WebUrl

interface Encoder {
    val predicate: (WebUrl) -> Boolean
    val encode: (WebUrl) -> DeepLink
}