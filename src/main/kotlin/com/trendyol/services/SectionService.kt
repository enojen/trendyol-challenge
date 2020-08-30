package com.trendyol.services

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.trendyol.SectionNotFoundException
import com.trendyol.replaceTurkishChars
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class SectionService {

    @Value("\${section.mapping.file}")
    val mappingFile: String = "classpath:sections.json"

    private var idName = mutableMapOf<Int, String>()
    private var nameId = mutableMapOf<String, Int>()

    fun getSectionName(sectionId: Int): String {
        return idName[sectionId] ?: throw SectionNotFoundException("Section with id=$sectionId not found")
    }

    fun getSectionId(sectionName: String): Int {
        return nameId[sectionName] ?: throw SectionNotFoundException("Section with name=$sectionName not found")
    }

    @PostConstruct
    fun init() {
        val json = DefaultResourceLoader().getResource(mappingFile).file.readText(charset = Charsets.UTF_8)
        val mapper = jacksonObjectMapper()
        val sections: List<Section> = mapper.readValue(json)

        sections.forEach {
            val name = it.name.replaceTurkishChars().toLowerCase()
            idName[it.id] = name
            nameId[name] = it.id
        }
    }

    private class Section(val id: Int, val name: String)
}