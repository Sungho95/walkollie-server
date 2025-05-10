package com.richbasoft.ollie.domain.member.dto

import com.querydsl.core.annotations.QueryProjection
import com.richbasoft.ollie.domain.member.entity.Member
import com.richbasoft.ollie.domain.member.enums.Status
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema
data class MemberResponseDto(
    @field:Schema(example = "1")
    val memberId: Long,

    @field:Schema(example = "올리")
    val deviceId: String,

    @field:Schema(example = "example@example.com")
    val email: String,

    @field:Schema(example = "ACTIVE")
    val status: Status,

    @field:Schema(example = "Android")
    var os: String,

    @field:Schema(example = "14")
    var osVersion: String,

    @field:Schema(example = "SM-F731")
    var deviceModel: String,

    @field:Schema(example = "true")
    var pushNotification: Boolean,

    @field:Schema(example = "false")
    var marketingNotification: Boolean,

    @field:Schema(example = "2023-09-17 01:12:41")
    val createdAt: LocalDateTime,

    @field:Schema(example = "2023-09-17 01:12:41")
    val modifiedAt: LocalDateTime,

    @field:Schema(example = "2023-09-17 01:12:41")
    val lastLoggedInAt: LocalDateTime
) {
    companion object {
        fun from(member: Member): MemberResponseDto {
            return MemberResponseDto(
                memberId = member.id!!,
                deviceId = member.deviceId,
                email = member.email,
                status = member.status,
                os = member.device.os,
                osVersion = member.device.osVersion,
                deviceModel = member.device.deviceModel,
                pushNotification = member.notificationConsent.pushNotification,
                marketingNotification = member.notificationConsent.marketingNotification,
                createdAt = member.createdAt,
                modifiedAt = member.modifiedAt,
                lastLoggedInAt = member.lastLoggedInAt
            )
        }
    }
}

@Schema
data class NotificationAccessTokenDto @QueryProjection constructor(
    @field:Schema(example = "asdasd1jk2hne123!234")
    val accessToken: String
)