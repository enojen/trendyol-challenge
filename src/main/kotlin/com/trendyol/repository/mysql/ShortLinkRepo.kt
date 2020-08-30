package com.trendyol.repository.mysql

import com.trendyol.model.ShortLink
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortLinkRepo : CrudRepository<ShortLink, String>