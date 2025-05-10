package com.richbasoft.ollie.common.security.auth.handler

import com.richbasoft.ollie.common.utils.ErrorResponder
import com.richbasoft.ollie.common.utils.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class MemberAccessDeniedHandler : AccessDeniedHandler {
    private val log = logger()

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        ErrorResponder().sendErrorResponse(response, HttpStatus.NOT_FOUND)
        log.warn("### 접근 권한 에러 발생: {}", accessDeniedException.message)
    }
}
