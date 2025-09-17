package pl.xsware.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
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

        val allRoles = securityProperties.users.flatMap { it.roles }.map { it.uppercase() }.toSet()
        val nonSwaggerRoles = allRoles.filter { it != "SWAGGER" }.toTypedArray()

        http
            .csrf { it.disable() }
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, _ ->
                    response.status = 401
                    response.contentType = "application/json"
                    response.writer.write("""{"error":"Unauthorized","message":"AUTH_REQUIRED"}""")
                }
                it.accessDeniedHandler { _, response, _ ->
                    response.status = 403
                    response.contentType = "application/json"
                    response.writer.write("""{"error":"Forbidden"}""")
                }
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml"
                    ).hasRole("SWAGGER")
                if (nonSwaggerRoles.isNotEmpty()) {
                    auth.anyRequest().hasAnyRole(*nonSwaggerRoles)
                } else {
                    auth.anyRequest().authenticated()
                }
            }
            .httpBasic(Customizer.withDefaults())

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}