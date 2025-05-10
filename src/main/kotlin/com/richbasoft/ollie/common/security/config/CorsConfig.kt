package com.richbasoft.ollie.common.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        configuration.apply {
            allowedOrigins = listOf("http://localhost", "http://localhost:8080")
            allowCredentials = true
            addExposedHeader("Authorization")
            addExposedHeader("Refresh")
            addAllowedHeader("*")
            allowedMethods = listOf("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
            configuration.maxAge = 3600L
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }
}