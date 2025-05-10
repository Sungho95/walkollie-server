package com.richbasoft.ollie.interfaces.internal.ollie

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.domain.ollie.service.OllieReadService
import com.richbasoft.ollie.common.utils.annotation.swagger.OllieReadApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.SelectedTitleReadApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.WornItemReadApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Ollie", description = "올리 API")
@RestController
@RequestMapping("/api/v1/ollie")
class OllieReadController(
    private val ollieReadService: OllieReadService
) : BaseController() {

    @Operation(
        summary = "올리 정보 조회",
        description = "회원 아이디(memberId)를 통해 올리의 정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @OllieReadApiResponses
    @GetMapping("/{member-id}")
    fun getOllieInfo(
        @PathVariable("member-id") memberId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = ollieReadService.getOllieInfo(memberId, loginMemberId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "올리 착용중인 아이템 리스트 조회",
        description = "회원 아이디(memberId)를 통해 올리의 착용중인 아이템 정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @WornItemReadApiResponses
    @GetMapping("/{member-id}/worn")
    fun getWornItemList(
        @PathVariable("member-id") memberId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val responses = ollieReadService.getWornItemList(memberId, loginMemberId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }

    @Operation(
        summary = "올리 사용중인 칭호 리스트 조회",
        description = "회원 아이디(memberId)를 통해 올리의 사용중인 칭호 정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @SelectedTitleReadApiResponses
    @GetMapping("/{member-id}/select")
    fun getSelectedTitleList(
        @PathVariable("member-id") memberId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val responses = ollieReadService.getSelectedTitleList(memberId, loginMemberId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }
}