package com.richbasoft.ollie.interfaces.internal.member

import com.richbasoft.ollie.domain.member.service.MemberUpdateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.NotificationConsentUpdateApiResponses
import com.richbasoft.ollie.domain.member.dto.NotificationConsentPatchDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/api/v1/member")
class MemberUpdateController(

    private val memberUpdateService: MemberUpdateService

) : BaseController() {

    @Operation(
        summary = "알림 동의 정보 수정",
        description = "사용자의 푸시/마케팅 알림 동의 정보를 수정합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @NotificationConsentUpdateApiResponses
    @PatchMapping("/notification")
    fun changeNotification(
        @Valid @RequestBody notificationConsentPatchDto: NotificationConsentPatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = memberUpdateService.changeNotificationConsent(notificationConsentPatchDto, loginMemberId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    /**
     * TODO 1. OAuth2 연동 기능 필요
     * TODO 2. OAuth2 연동 시 email 업데이트 필요
     * TODO 3. 기기 변경 시 deviceId 업데이트 필요
     */
}