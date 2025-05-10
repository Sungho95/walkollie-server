package com.richbasoft.ollie.common.utils.annotation.swagger

import com.richbasoft.ollie.common.exception.response.ErrorResponse
import com.richbasoft.ollie.domain.inquiry.dto.response.AnswerInfoDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDetailDto
import com.richbasoft.ollie.domain.inquiry.dto.response.InquiryInfoDto
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "201",
            description = "Successful Create",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = InquiryInfoDetailDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class InquiryCreateApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = InquiryInfoDetailDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class InquiryReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = InquiryInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class InquiryListReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = InquiryInfoDetailDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class InquiryUpdateApiResponses

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
annotation class InquiryDeleteApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AnswerInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class InquiryAnswerReadApiResponses