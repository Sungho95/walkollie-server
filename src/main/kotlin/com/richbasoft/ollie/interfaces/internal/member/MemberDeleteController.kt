package com.richbasoft.ollie.interfaces.internal.member

import com.richbasoft.ollie.domain.member.service.MemberDeleteService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.MemberDeleteApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/api/v1/member")
class MemberDeleteController(

    private val memberDeleteService: MemberDeleteService

) : BaseController() {

    @Operation(
        summary = "회원탈퇴",
        description = "회원 아이디(memberId)를 통해 회원탈퇴합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @MemberDeleteApiResponses
    @DeleteMapping("{member-id}")
    fun deleteMember(
        @PathVariable("member-id") memberId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<Unit> {
        memberDeleteService.deleteMember(memberId, loginMemberId)

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build()
    }
}