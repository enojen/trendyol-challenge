package com.trendyol.services

import com.trendyol.SectionNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SectionServiceTest {

    private val sectionService = SectionService()

    init {
        sectionService.init()
    }

    @Test
    fun `get section name by id`() {
        val result = sectionService.getSectionName(2)
        assertEquals("erkek", result)
    }

    @Test
    fun `get section id by name`() {
        val result = sectionService.getSectionId("supermarket")
        assertEquals(3, result)
    }

    @Test
    fun `throw exception if section not found`() {
        assertThrows<SectionNotFoundException> {
            sectionService.getSectionId("dummy")
        }
    }
}