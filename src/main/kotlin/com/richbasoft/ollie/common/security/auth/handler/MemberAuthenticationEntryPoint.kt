package com.richbasoft.ollie.common.security.auth.handler

import com.richbasoft.ollie.common.utils.ErrorResponder
import com.richbasoft.ollie.common.utils.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class MemberAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val log: Logger = logger()

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        val exception = request.getAttribute("exception") as? Exception
        ErrorResponder().sendErrorResponse(response, HttpStatus.UNAUTHORIZED)

        logExceptionMessage(authException, exception)
    }

    private fun logExceptionMessage(authException: AuthenticationException?, exception: Exception?) {
        val message = exception?.message ?: authException?.message
        log.warn("### 로그인 인증 실패: {}", message)
    }
}