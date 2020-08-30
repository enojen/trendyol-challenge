package com.trendyol.model

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "short_links")
class ShortLink(
        @Id
        var shortLink: String,
        var deepLink: String,
        var link: String,
        var createdAt: LocalDateTime = LocalDateTime.now()
) : Serializable

