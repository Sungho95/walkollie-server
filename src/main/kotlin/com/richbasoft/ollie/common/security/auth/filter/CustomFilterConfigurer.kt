package com.richbasoft.ollie.common.security.auth.filter

import com.richbasoft.ollie.common.security.auth.handler.MemberAuthenticationFailureHandler
import com.richbasoft.ollie.common.security.auth.handler.MemberAuthenticationSuccessHandler
import com.richbasoft.ollie.common.security.auth.jwt.JwtTokenProvider
import com.richbasoft.ollie.common.security.auth.utils.CustomAuthorityUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer

class CustomFilterConfigurer(
    private val jwtTokenProvider: JwtTokenProvider,
    private val authorityUtils: CustomAuthorityUtils,
) : AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val authenticationManager = builder.getSharedObject(AuthenticationManager::class.java)

        val jwtAuthenticationFilter = JwtAuthenticationFilter(
            authenticationManager,
            jwtTokenProvider
        )
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/member/login")
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(MemberAuthenticationSuccessHandler())
        jwtAuthenticationFilter.setAuthenticationFailureHandler(MemberAuthenticationFailureHandler())

        val jwtVerificationFilter = JwtVerificationFilter(jwtTokenProvider, authorityUtils)

        builder
            .addFilter(jwtAuthenticationFilter)
            .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter::class.java)
    }
}