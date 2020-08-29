package muhas

import com.couchbase.client.java.Bucket
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories


@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class MuhasApplication


fun main(args: Array<String>) {
    runApplication<MuhasApplication>(*args)
}



@Configuration
@EnableCouchbaseRepositories(basePackages = ["com.muhas.repository"])
@CacheConfig
@EnableCaching
class MyCouchbaseConfig : AbstractCouchbaseConfiguration() {

    override fun getConnectionString(): String {
        return "localhost"
    }

    override fun getPassword(): String {
        return "123456"
    }

    override fun getBucketName(): String {
        return "test"
    }

    override fun getUserName(): String {
        return "root"
    }

    @Bean
    @Throws(Exception::class)
    fun campusBucket(): Bucket {

        return couchbaseCluster(couchbaseClusterEnvironment()).bucket("test")
    }
}