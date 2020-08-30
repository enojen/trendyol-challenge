package com.trendyol.repository.mysql

import com.trendyol.model.LinkConversion
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkConversionRepo : CrudRepository<LinkConversion, Long>


