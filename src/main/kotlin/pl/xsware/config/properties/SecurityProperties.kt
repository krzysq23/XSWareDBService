package pl.xsware.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "security")
class SecurityProperties {

    var users: List<User> = emptyList()

    class User {
        lateinit var username: String
        lateinit var password: String
        var roles: List<String> = emptyList()
    }
}