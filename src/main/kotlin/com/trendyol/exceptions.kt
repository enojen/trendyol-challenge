package com.trendyol

import java.lang.RuntimeException


class ShortLinkNotFoundException: RuntimeException()

class SectionNotFoundException(msg: String = ""): RuntimeException(msg)