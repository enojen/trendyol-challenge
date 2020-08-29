package muhas.services

import muhas.ShortLink
import muhas.repository.mysql.ShortLinkRepo
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*


@Service
class ShortLinkService(val shortLinkRepo: ShortLinkRepo) {

    @Cacheable(key = "#id", cacheNames = ["cache1"])
    fun findById(id: String ): Optional<ShortLink> {
        Thread.sleep(5000)
        return shortLinkRepo.findById(id)
    }

}