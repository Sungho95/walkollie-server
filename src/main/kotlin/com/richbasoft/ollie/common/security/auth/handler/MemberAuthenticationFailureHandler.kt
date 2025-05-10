package com.richbasoft.ollie.common.security.auth.handler

import com.richbasoft.ollie.common.exception.response.ErrorResponse
import com.richbasoft.ollie.common.utils.customObjectMapper
import com.richbasoft.ollie.common.utils.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

class MemberAuthenticationFailureHandler : AuthenticationFailureHandler {
    private val log = logger()

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        log.error("### 로그인 인증 실패: {}", exception.message)
        sendErrorResponse(response)
    }

    private fun sendErrorResponse(response: HttpServletResponse) {
        val objectMapper = customObjectMapper()
        val errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()

        objectMapper.writeValue(response.writer, errorResponse)
    }
}