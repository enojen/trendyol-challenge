package muhas.repository.redis

import org.springframework.data.redis.support.atomic.RedisAtomicLong
import org.springframework.stereotype.Repository


@Repository
class CounterRepo(val shortLinkCounter: RedisAtomicLong) {

    fun getId(): Long {
        return shortLinkCounter.incrementAndGet()
    }
}