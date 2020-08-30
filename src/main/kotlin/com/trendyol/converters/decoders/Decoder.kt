package com.trendyol.converters.decoders

import com.trendyol.model.TyDeepLink
import com.trendyol.model.TyLink

interface Decoder {
    val predicate: (TyDeepLink) -> Boolean
    val decode: (TyDeepLink) -> TyLink
}