package com.trendyol.web

import com.fasterxml.jackson.annotation.JsonAnyGetter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


class Result private constructor(private val httpStatus: HttpStatus) {

    private val fields = mutableMapOf<String, Any>()

    fun add(key: String, value: Any): Result {
        this.fields[key] = value
        return this
    }

    fun message(value: Any): Result {
        this.fields["message"] = value
        return this
    }

    @JsonAnyGetter
    fun getFields(): Map<String, Any> {
        return fields
    }

    fun build(): ResponseEntity<Result> {
        return ResponseEntity(this, this.httpStatus)
    }

    companion object {

        fun Error(status: HttpStatus): Result {
            return Result(status)
        }

        fun Success(): Result {
            return Result(HttpStatus.OK)
        }
    }
}