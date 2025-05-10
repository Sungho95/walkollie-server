package com.richbasoft.ollie.common.utils.annotation.swagger

import com.richbasoft.ollie.common.exception.response.ErrorResponse
import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
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
                schema = Schema(implementation = CalendarInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class CalendarCreateApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = CalendarInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class CalendarReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = CalendarInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class CalendarListReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = Long::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class TotalStepsReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Update",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = CalendarInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class CalendarUpdateApiResponses

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
annotation class CalendarDeleteApiResponses