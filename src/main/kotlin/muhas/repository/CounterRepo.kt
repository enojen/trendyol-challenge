package muhas.repository

import com.couchbase.client.java.Bucket
import com.couchbase.client.java.kv.IncrementOptions
import org.springframework.stereotype.Repository


@Repository
class CounterRepo(val campusBucket: Bucket) {

    fun getId(): Long {
        val a = campusBucket.defaultCollection().binary()
                .increment("shortLinkId", IncrementOptions.incrementOptions().initial(100000))
        return a.content()
    }
}