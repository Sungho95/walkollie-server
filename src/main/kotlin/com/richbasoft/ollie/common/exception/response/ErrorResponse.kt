package com.richbasoft.ollie.common.exception.response

import com.richbasoft.ollie.common.exception.ExceptionCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolation
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import java.time.LocalDateTime

data class ErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int = 0,
    val message: String = "",
    val path: String = "",
    val fieldErrors: List<FieldError> = listOf(),
    val violationErrors: List<ConstraintViolationError>? = listOf()
) {
    companion object {
        fun of(exceptionCode: ExceptionCode, httpServletRequest: HttpServletRequest): ErrorResponse {

            return ErrorResponse(
                timestamp = LocalDateTime.now(),
                status = exceptionCode.status,
                message = exceptionCode.message,
                path = httpServletRequest.requestURI
            )
        }

        fun of(httpStatus: HttpStatus): ErrorResponse {

            return ErrorResponse(
                timestamp = LocalDateTime.now(),
                status = httpStatus.value(),
                message = httpStatus.reasonPhrase
            )
        }

        fun of(message: String?): ErrorResponse {
            return ErrorResponse(
                timestamp = LocalDateTime.now(),
                message = message ?: ""
            )
        }

        fun of(httpStatus: HttpStatus, message: String): ErrorResponse {

            return ErrorResponse(
                timestamp = LocalDateTime.now(),
                status = httpStatus.value(),
                message = message
            )
        }

        fun of(bindingResult: BindingResult): ErrorResponse {

            val fieldErrors = FieldError.of(bindingResult)

            return ErrorResponse(fieldErrors = fieldErrors)
        }

        fun of(violations: Set<ConstraintViolation<*>>): ErrorResponse {

            val violationErrors = ConstraintViolationError.of(violations)

            return ErrorResponse(violationErrors = violationErrors)
        }
    }

    data class FieldError(
        val field: String,
        val rejectedValue: Any?,
        val reason: String
    ) {
        companion object {
            fun of(bindingResult: BindingResult): List<FieldError> {

                return bindingResult.fieldErrors.map { error ->
                    FieldError(
                        field = error.field,
                        rejectedValue = error.rejectedValue?.toString() ?: "",
                        reason = error.defaultMessage ?: ""
                    )
                }
            }
        }
    }

    data class ConstraintViolationError(
        val propertyPath: String,
        val rejectedValue: Any?,
        val reason: String
    ) {
        companion object {
            fun of(constraintViolations: Set<ConstraintViolation<*>>): List<ConstraintViolationError> {

                return constraintViolations.map { violation ->
                    ConstraintViolationError(
                        propertyPath = violation.propertyPath.toString(),
                        rejectedValue = violation.invalidValue?.toString() ?: "",
                        reason = violation.message
                    )
                }
            }
        }
    }
}
