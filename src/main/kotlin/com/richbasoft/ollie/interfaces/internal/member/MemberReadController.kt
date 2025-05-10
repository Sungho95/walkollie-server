package com.richbasoft.ollie.interfaces.internal.member

import com.richbasoft.ollie.domain.member.service.MemberReadService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.MemberCheckApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.MemberReadApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/api/v1/member")
class MemberReadController(

    private val memberReadService: MemberReadService

) : BaseController() {

    @Operation(
        summary = "회원 여부 체크",
        description = "deviceId를 통해 회원 여부를 확인합니다.",
        security = [SecurityRequirement(name = "public")]
    )
    @MemberCheckApiResponses
    @GetMapping("/check")
    fun memberCheck(@RequestParam deviceId: String): ResponseEntity<*> {
        val response = memberReadService.memberCheck(deviceId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "회원 상세 조회",
        description = "회원 아이디(memberId)를 통해 회원정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @MemberReadApiResponses
    @GetMapping("{member-id}")
    fun getMemberInfo(
        @PathVariable("member-id") memberId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = memberReadService.getMemberInfo(memberId, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "회원 상세 리스트 조회",
        description = "회원정보 리스트를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @MemberReadApiResponses
    @GetMapping
    fun getMemberInfoList(): ResponseEntity<*> {
        val responses = memberReadService.getMemberInfoList()
        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }
}