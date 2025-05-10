package com.richbasoft.ollie.common.utils.annotation.swagger

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerInfoDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDetailDto
import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminInquiryInfoDto
import com.richbasoft.ollie.common.exception.response.ErrorResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AdminInquiryInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminInquiryListReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AdminInquiryInfoDetailDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminInquiryReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "201",
            description = "Successful Create",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AdminAnswerInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminAnswerCreateApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Update",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AdminAnswerInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminAnswerUpdateApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "204",
            description = "Successful Delete",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = Unit::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminAnswerDeleteApiResponses