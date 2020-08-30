package com.trendyol.model

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "link_conversions")
class LinkConversion(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var source: String,
        var target: String,
        var createdAt: LocalDateTime = LocalDateTime.now()
)