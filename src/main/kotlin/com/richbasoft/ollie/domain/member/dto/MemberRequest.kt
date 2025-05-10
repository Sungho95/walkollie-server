package com.richbasoft.ollie.domain.member.dto

import com.richbasoft.ollie.domain.member.entity.Device
import com.richbasoft.ollie.domain.member.entity.Member
import com.richbasoft.ollie.domain.member.entity.NotificationConsent
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema
data class MemberPostDto(
    @field:Schema(example = "5666040ce2d36b43")
    @field:NotBlank(message = "deviceId는 필수값 입니다.")
    val deviceId: String,

    @field:Schema(example = "암호화된 키 값")
    @field:NotBlank(message = "password는 필수값 입니다.")
    val password: String,

    @field:Schema(example = "example@example.com (필수 아님, OAuth2 연동시 필요)")
    val email: String = "",

    @field:Schema(example = "Android")
    @field:NotBlank(message = "os는 필수값 입니다.")
    var os: String,

    @field:Schema(example = "14")
    @field:NotBlank(message = "osVersion은 필수값 입니다.")
    var osVersion: String,

    @field:Schema(example = "SM-F731")
    @field:NotBlank(message = "deviceModel은 필수값 입니다.")
    var deviceModel: String,

    @field:Schema(example = "true")
    var pushNotification: Boolean,

    @field:Schema(example = "false")
    var marketingNotification: Boolean
) {
    fun toEntity(): Member {
        return Member(
            deviceId,
            password,
            email,
            Device(os, osVersion, deviceModel),
            NotificationConsent(false, false, "")
        )
    }
}

data class NotificationConsentPatchDto(
    val pushNotification: Boolean,
    val marketingNotification: Boolean,
    val accessToken: String
)