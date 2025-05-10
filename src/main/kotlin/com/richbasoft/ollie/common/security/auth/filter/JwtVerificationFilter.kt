package com.richbasoft.ollie.common.security.auth.filter

import com.richbasoft.ollie.common.security.auth.jwt.JwtTokenProvider
import com.richbasoft.ollie.common.security.auth.memberdetails.MemberDetails
import com.richbasoft.ollie.common.security.auth.utils.CustomAuthorityUtils
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.security.SignatureException

@Suppress("UNCHECKED_CAST")
class JwtVerificationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val authorityUtils: CustomAuthorityUtils
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val claims = verifyJws(request)
            setAuthenticationToContext(claims)
        } catch (se: SignatureException) {
            request.setAttribute("exception", se)
        } catch (ee: ExpiredJwtException) {
            request.setAttribute("exception", ee)
        } catch (e: Exception) {
            request.setAttribute("exception", e)
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val authorization = request.getHeader("Authorization")
        return authorization == null || !authorization.startsWith("Bearer ")
    }

    private fun verifyJws(request: HttpServletRequest): Map<String, Any> {
        val jws = request.getHeader("Authorization").replace("Bearer ", "")

        return jwtTokenProvider.getClaims(jws).body
    }

    private fun setAuthenticationToContext(claims: Map<String, Any>) {
        val id = claims["id"] as String
        val deviceId = claims["sub"] as String
        val roles = claims["roles"] as List<String>

        val authorities = authorityUtils.createAuthorities(roles)
        val memberDetails = MemberDetails.from(id.toLong(), deviceId, roles)

        val authentication = UsernamePasswordAuthenticationToken(memberDetails, null, authorities)

        SecurityContextHolder.getContext().authentication = authentication
    }
}