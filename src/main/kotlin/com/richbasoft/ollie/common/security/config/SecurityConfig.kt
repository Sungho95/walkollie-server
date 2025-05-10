package com.richbasoft.ollie.common.security.config

import com.richbasoft.ollie.common.security.auth.filter.CustomFilterConfigurer
import com.richbasoft.ollie.common.security.auth.handler.MemberAccessDeniedHandler
import com.richbasoft.ollie.common.security.auth.handler.MemberAuthenticationEntryPoint
import com.richbasoft.ollie.domain.member.enums.Role
import com.richbasoft.ollie.common.security.auth.jwt.JwtTokenProvider
import com.richbasoft.ollie.common.security.auth.utils.CustomAuthorityUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val corsConfig: CorsConfig,
    private val jwtTokenProvider: JwtTokenProvider,
    private val authorityUtils: CustomAuthorityUtils,
//    private val customOAuth2UserService: CustomOAuth2UserService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .headers { headers ->
                headers.frameOptions {
                    it.sameOrigin()
                }
            }
            .csrf { it.disable() }
            .cors {
                it.configurationSource(corsConfig.corsConfigurationSource())
            }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .exceptionHandling {
                it.authenticationEntryPoint(MemberAuthenticationEntryPoint())
                it.accessDeniedHandler(MemberAccessDeniedHandler())
            }
            .apply(CustomFilterConfigurer(jwtTokenProvider, authorityUtils))
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/v3/api-docs/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/v1/member").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/member/check").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/v1/member/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/v1/member/logout").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/v1/member/refresh").permitAll()
            .anyRequest().hasAnyRole(Role.USER.name)
            .and()
            .logout {
                it.logoutSuccessUrl("/login")
            }
//            .oauth2Login {
//                it.userInfoEndpoint()
//                    .userService(customOAuth2UserService)
//                it.successHandler(oauth2MemberAuthenticationSuccessHandler())
//            }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun ignoreCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer {
            it.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/v1/api-docs/**")
        }
    }

//    @Bean
//    fun oauth2MemberAuthenticationSuccessHandler(): OAuth2MemberAuthenticationSuccessHandler {
//        return OAuth2MemberAuthenticationSuccessHandler(jwtTokenProvider)
//    }
}
