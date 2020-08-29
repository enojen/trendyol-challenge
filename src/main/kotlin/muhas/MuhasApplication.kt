package muhas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.support.atomic.RedisAtomicLong


@SpringBootApplication
@EnableCaching
class MuhasApplication


fun main(args: Array<String>) {
    runApplication<MuhasApplication>(*args)
}


@Configuration
@EnableRedisRepositories(value = ["muhas.repository.redis"])
@EnableJpaRepositories(value = ["muhas.repository.mysql"])
class Config {

    @Bean
    fun redisTemplate(connectionFactory:RedisConnectionFactory): RedisTemplate<*, *> {
        val template = RedisTemplate<Any, Any>()
        template.setConnectionFactory(connectionFactory)
        template.setDefaultSerializer(GenericJackson2JsonRedisSerializer())
        return template
    }

    @Bean
    fun shortLinkCounter(connectionFactory:RedisConnectionFactory): RedisAtomicLong {
        return RedisAtomicLong("SHORTLINK_COUNTER", connectionFactory)
    }
}
