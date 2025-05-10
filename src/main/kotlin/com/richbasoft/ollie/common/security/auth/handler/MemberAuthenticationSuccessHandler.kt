package com.richbasoft.ollie.common.security.auth.handler

import com.richbasoft.ollie.common.security.auth.dto.LoginResponseDto
import com.richbasoft.ollie.common.security.auth.memberdetails.MemberDetails
import com.richbasoft.ollie.common.utils.customObjectMapper
import com.richbasoft.ollie.common.utils.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class MemberAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    private val log: Logger = logger()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val principal = authentication.principal as MemberDetails

        log.info("### 로그인 인증 성공: id={}, deviceId={} ", principal.id, principal.deviceId)
        sendLoginResponse(principal, response)
    }

    private fun sendLoginResponse(
        principal: MemberDetails,
        response: HttpServletResponse
    ) {
        val objectMapper = customObjectMapper()
        val loginResponseDto = LoginResponseDto(principal.id)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.OK.value()

        objectMapper.writeValue(response.writer, loginResponseDto)
    }
}