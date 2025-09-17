package pl.xsware.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openAPI(): OpenAPI {
        val basicSchemeName = "basicAuth"

        return OpenAPI()
            .info(
                Info()
                    .title("XSware DB Service")
                    .version("v1")
                    .description("Dokumentacja API (Basic Auth)")
                    .contact(Contact().name("XSware").url("http://connector.xsware.p"))
                    .license(License().name("Apache-2.0"))
            )
            .components(
                Components().addSecuritySchemes(
                    basicSchemeName,
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")
                )
            )
            .addSecurityItem(SecurityRequirement().addList(basicSchemeName))
    }
}