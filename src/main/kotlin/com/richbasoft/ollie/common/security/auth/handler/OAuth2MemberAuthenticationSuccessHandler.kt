//package com.richbasoft.ollie.global.security.auth.handler
//
//import com.richbasoft.ollie.global.security.auth.jwt.JwtTokenProvider
//import com.richbasoft.ollie.global.security.auth.oauth2.dto.CustomOAuth2User
//import com.richbasoft.ollie.global.utils.logger
//import jakarta.servlet.FilterChain
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.slf4j.Logger
//import org.springframework.security.core.Authentication
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
//import org.springframework.util.LinkedMultiValueMap
//import org.springframework.web.util.UriComponentsBuilder
//import java.net.URI
//
//class OAuth2MemberAuthenticationSuccessHandler(
//
//    private val jwtTokenProvider: JwtTokenProvider,
//
//) : SimpleUrlAuthenticationSuccessHandler() {
//
//    val log: Logger = logger()
//
//    override fun onAuthenticationSuccess(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        chain: FilterChain,
//        authentication: Authentication
//    ) {
//        val oAuth2User: CustomOAuth2User = authentication.principal as CustomOAuth2User
//        log.info("oAuth2User={}", oAuth2User)
//
//        val email = oAuth2User.email
//        val roles = oAuth2User.authorities
//            .map { it.authority.substring(5) }
//            .toList()
//        log.info("roles={}", roles)
//
//        redirect(request, response, email, roles)
//    }
//
//    private fun redirect(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        email: String,
//        roles: List<String>
//    ) {
//        val accessToken = delegateAccessToken(email, roles)
//        val refreshToken = delegateRefreshToken(email)
//        val uri = createURI(accessToken, refreshToken).toString()
//
//        response.setHeader("Authorization", "Bearer $accessToken")
//        response.setHeader("Refresh", refreshToken)
//
//        redirectStrategy.sendRedirect(request, response, uri)
//    }
//
//    private fun delegateAccessToken(email: String, roles: List<String>): String {
//        val claims = mutableMapOf<String, Any>()
//        claims.put("email", email)
//        claims.put("roles", roles)
//
//        val subject = email
//        val expiration = jwtTokenProvider.getTokenExpiration(jwtTokenProvider.accessTokenExpirationMinutes)
//        val accessToken = jwtTokenProvider.generateAccessToken(claims, subject, expiration)
//
//        return accessToken
//    }
//
//    private fun delegateRefreshToken(email: String): String {
//        val subject = email
//        val expiration = jwtTokenProvider.getTokenExpiration(jwtTokenProvider.refreshTokenExpirationMinutes)
//
//        return jwtTokenProvider.generateRefreshToken(subject, expiration)
//    }
//
//    private fun createURI(accessToken: String, refreshToken: String): URI {
//        val queryParams: LinkedMultiValueMap<String, String> = LinkedMultiValueMap<String, String>()
//        queryParams.add("access_token", accessToken)
//        queryParams.add("refresh_token", refreshToken)
//
//        return UriComponentsBuilder
//            .newInstance()
//            .scheme("http")
//            .port("8080")
//            .host("localhost")
//            .path("/")
//            .queryParams(queryParams)
//            .build()
//            .toUri()
//    }
//}
