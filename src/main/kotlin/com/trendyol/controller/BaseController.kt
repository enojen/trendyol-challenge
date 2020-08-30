package com.trendyol.controller

import com.trendyol.web.Result
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler

abstract class BaseController {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleException(e: Exception): Any {
        return Result.Error(HttpStatus.BAD_REQUEST)
                .message("Invalid input")
                .add("details", e.cause?.message ?: "")
                .build()
    }
}