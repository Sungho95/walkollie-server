package com.richbasoft.ollie.common.utils.annotation.swagger

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemResponseDto
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
            responseCode = "201",
            description = "Successful Create",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AdminItemResponseDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminItemCreateApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AdminItemResponseDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminItemReadApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AdminItemResponseDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminItemUpdateApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "204",
            description = "Successful Delete",
            content = [Content()]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class AdminItemDeleteApiResponses
