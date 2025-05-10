package com.richbasoft.ollie.common.security.auth.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.richbasoft.ollie.common.security.auth.dto.LoginRequestDto
import com.richbasoft.ollie.common.security.auth.jwt.JwtTokenProvider
import com.richbasoft.ollie.common.security.auth.memberdetails.MemberDetails
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtAuthenticationFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        val objectMapper = ObjectMapper()
        val loginRequestDto = objectMapper.readValue(request.inputStream, LoginRequestDto::class.java)
        val authenticationToken =
            UsernamePasswordAuthenticationToken(loginRequestDto.deviceId, loginRequestDto.password)
        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val memberDetails = authResult.principal as MemberDetails

        val accessToken = jwtTokenProvider.delegateAccessToken(memberDetails)
        val refreshToken = jwtTokenProvider.delegateRefreshToken(memberDetails)

        response.setHeader("Authorization", "Bearer $accessToken")
        response.setHeader("Refresh", refreshToken)

        this.successHandler.onAuthenticationSuccess(request, response, authResult)
    }
}