package com.trendyol.repository.mysql

import com.trendyol.model.UrlRequest
import org.springframework.data.repository.CrudRepository
import javax.persistence.*

interface UrlRequestRepo : CrudRepository<UrlRequest, Long> {

}


