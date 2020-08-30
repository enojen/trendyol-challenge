package muhas.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table
class ShortLink(
        @javax.persistence.Id
        var shortLink: String,
        var deepLink: String,
        var webUrl: String
) : Serializable

