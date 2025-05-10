package com.richbasoft.ollie.common.utils.annotation.swagger

import com.richbasoft.ollie.domain.item.dto.ItemInfoDto
import com.richbasoft.ollie.common.exception.response.ErrorResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

annotation class ItemCreateApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "200",
            description = "Successful Read",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ItemInfoDto::class)
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Error",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]
        )
    ]
)
annotation class ItemReadApiResponses

annotation class ItemUpdateApiResponses

annotation class ItemDeleteApiResponses