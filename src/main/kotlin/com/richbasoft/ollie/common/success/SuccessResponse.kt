package com.richbasoft.ollie.common.success

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

data class SuccessResponse<T>(
    val data: T?,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val message: String,
    val path: String
) {
    companion object {
        fun <T> successOf(
            successCode: SuccessCode,
            data: T?,
            httpServletRequest: HttpServletRequest
        ): ResponseEntity<SuccessResponse<T>> {
            return ResponseEntity.ok(
                SuccessResponse(
                    data = data,
                    timestamp = LocalDateTime.now(),
                    status = successCode.statusCode.status,
                    message = successCode.message,
                    path = httpServletRequest.requestURI
                )
            )
        }
    }
}