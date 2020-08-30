package com.trendyol.repository.mysql

import com.trendyol.model.UrlRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Repository
interface UrlRequestRepo : CrudRepository<UrlRequest, Long> {

}


