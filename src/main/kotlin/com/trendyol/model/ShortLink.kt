package com.trendyol.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "short_links")
class ShortLink(
        @Id
        var shortLink: String,
        var deepLink: String,
        var link: String
) : Serializable

