package muhas.repository.mysql

import muhas.model.ShortLink
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ShortLinkRepo : CrudRepository<ShortLink, String> {

}