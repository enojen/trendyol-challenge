package com.trendyol.converters.encoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink

interface Encoder {
    val predicate: (TyLink) -> Boolean
    val encode: (TyLink) -> TyDeepLink
}