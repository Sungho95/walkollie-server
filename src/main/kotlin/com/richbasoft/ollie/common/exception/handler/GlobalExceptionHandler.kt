package com.richbasoft.ollie.common.exception.handler

import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.response.ErrorResponse
import com.richbasoft.ollie.common.base.controller.BaseController
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler : BaseController() {

    @ExceptionHandler(BusinessLogicException::class)
    fun handleBusinessLogicException(
        e: BusinessLogicException,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<Any> {
        log.error("API 호출 에러: {}", e.toString())

        val errorResponse = ErrorResponse.of(e.exceptionCode, httpServletRequest)

        return ResponseEntity(errorResponse, HttpStatus.valueOf(e.exceptionCode.status))
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse {
        log.error("Validation 에러: {}", e.toString())

        return ErrorResponse.of(e.bindingResult)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodIllegalArgumentException(e: IllegalArgumentException): ErrorResponse {
        log.error("IllegalArgumentException 에러 발생: {}", e.toString())

        return ErrorResponse.of(e.message)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    fun handleConstraintViolationException(e: ConstraintViolationException): ErrorResponse {
        log.error("Violation 에러: {}", e.toString())

        return ErrorResponse.of(e.constraintViolations)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ErrorResponse {
        log.error("HttpRequestNotSupported 에러: {}", e.toString())

        return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler
    fun handleException(e: Exception): ErrorResponse {
        log.error("예외 발생: {}", e.toString())

        return ErrorResponse.of(e.message)
    }
}
