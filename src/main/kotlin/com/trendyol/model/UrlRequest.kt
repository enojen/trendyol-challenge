package com.trendyol.model

import javax.persistence.*


@Entity
@Table(name = "url_requests")
class UrlRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var webUrl: String,
        var deepLink: String
)