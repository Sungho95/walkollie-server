package com.richbasoft.ollie.common.utils

import com.richbasoft.ollie.common.exception.response.ErrorResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class ErrorResponder {
    fun sendErrorResponse(response: HttpServletResponse, status: HttpStatus) {
        val objectMapper = customObjectMapper()
        val errorResponse = ErrorResponse.of(status)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = status.value()
        objectMapper.writeValue(response.writer, errorResponse)
    }
}