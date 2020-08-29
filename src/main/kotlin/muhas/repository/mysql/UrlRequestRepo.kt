package muhas.repository.mysql

import org.springframework.data.repository.CrudRepository
import javax.persistence.*

interface UrlRequestRepo : CrudRepository<UrlRequest, Long> {

}


@Entity
@Table(name = "url_requests")
class UrlRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var webUrl: String,
        var deepLink: String
)