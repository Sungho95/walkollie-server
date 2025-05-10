package com.richbasoft.ollie.common.config

import com.richbasoft.ollie.common.security.auth.filter.JwtAuthenticationFilter
import io.swagger.v3.oas.models.*
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.parameters.RequestBody
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.responses.ApiResponses
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.security.web.FilterChainProxy
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer

@Profile("local", "develop")
@Configuration
class OpenAPIConfig(
    private val applicationContext: ApplicationContext
) {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes("bearerAuth", jwtSecurityScheme())
                    .addSecuritySchemes("public", publicSecurityScheme())
            )
            .security(arrayListOf(jwtSecurityRequirement()))
            .info(apiInfo())
    }

    @Bean
    fun loginEndpointCustomizer(): OpenApiCustomizer {
        val filterChainProxy: FilterChainProxy = applicationContext.getBean(
            AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME,
            FilterChainProxy::class.java
        )

        return OpenApiCustomizer { openAPI ->
            filterChainProxy.filterChains.forEach {
                val optionalFilter = it.filters
                    .filterIsInstance<JwtAuthenticationFilter>().firstOrNull()

                optionalFilter?.let {
                    val operation = Operation()
                    val schema = ObjectSchema().apply {
                        addProperty(it.usernameParameter, StringSchema())
                        addProperty(it.passwordParameter, StringSchema())
                    }
                    val requestBody = RequestBody()
                        .content(
                            Content().addMediaType(
                                org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                                MediaType().schema(schema)
                            )
                        )
                    operation.requestBody(requestBody)
                    val apiResponses = ApiResponses()
                        .addApiResponse(
                            HttpStatus.OK.value().toString(),
                            ApiResponse().description(HttpStatus.OK.reasonPhrase)
                        )
                        .addApiResponse(
                            HttpStatus.BAD_REQUEST.value().toString(),
                            ApiResponse().description(HttpStatus.BAD_REQUEST.reasonPhrase)
                        )
                    operation.responses(apiResponses)
                    operation.addTagsItem("Login")
                    val pathItem = PathItem().post(operation)
                    openAPI.paths.addPathItem("/api/v1/member/login", pathItem)
                }
            }
        }
    }

    private fun jwtSecurityScheme(): SecurityScheme? {
        val securityScheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .`in`(SecurityScheme.In.HEADER)
            .name("Authorization")
        return securityScheme
    }

    private fun jwtSecurityRequirement(): SecurityRequirement {
        val securityRequirement = SecurityRequirement()
        securityRequirement.addList("bearerAuth")
        return securityRequirement
    }

    private fun publicSecurityScheme(): SecurityScheme {
        return SecurityScheme()
            .name("public")
    }

    private fun apiInfo(): Info {
        return Info()
            .title("Richba Soft - Ollie API")
            .description("Ollie REST API Docs")
            .version("v1.0.0")
    }
}