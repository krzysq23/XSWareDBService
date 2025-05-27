package pl.xsware.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import pl.xsware.config.properties.SecurityProperties

@Configuration
class SecurityConfig(private val securityProperties: SecurityProperties) {

    @Bean
    fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val users = securityProperties.users.map {
            User.builder()
                .username(it.username)
                .password(passwordEncoder.encode(it.password))
                .roles(*it.roles.toTypedArray())
                .build()
        }
        return InMemoryUserDetailsManager(users)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            .httpBasic { }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}