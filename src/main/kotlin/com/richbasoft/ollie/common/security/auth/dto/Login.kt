package com.richbasoft.ollie.common.security.auth.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

@Schema
data class LoginRequestDto(
    @field:Schema(example = "5666040ce2d36b43")
    @field:NotBlank(message = "deviceId는 필수값 입니다.")
    val deviceId: String = "",

    @field:Schema(example = "암호화된 키 값")
    @field:NotBlank(message = "password는 필수값 입니다.")
    val password: String = ""
)

data class LoginResponseDto(
    val id: Long = 0,
    val timestamp: LocalDateTime = LocalDateTime.now()
)