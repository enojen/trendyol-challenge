package muhas.services

import org.springframework.stereotype.Service


@Service
class SectionService {

    fun getSectionName(sectionId: Int ): String {
        return "kadin"
    }

    fun getSectionId(sectionName: String): Int {
        return 1
    }
}