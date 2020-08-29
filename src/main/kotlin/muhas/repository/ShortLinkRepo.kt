package muhas.repository

import muhas.ShortLink
import org.springframework.data.couchbase.repository.CouchbaseRepository


interface ShortLinkRepo : CouchbaseRepository<ShortLink, String>