package com.richbasoft.ollie.common.security.auth.dto

import jakarta.validation.constraints.NotBlank

data class TokenRequestDto(
    @field:NotBlank
    val accessToken: String,

    @field:NotBlank
    val refreshToken: String
)